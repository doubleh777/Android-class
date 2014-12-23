package com.example.nextagram;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

public class ArticleViewerActivity extends ActionBarActivity implements OnClickListener{

	private ArrayList<Comment> commentList = new ArrayList<Comment>();
	private ArrayList<Comment> commentListForEachArticle = new ArrayList<Comment>();
	private Dao dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_viewer);
	
		TextView articleWriter = (TextView)findViewById(R.id.article_writer);
		ImageView articleImage = (ImageView)findViewById(R.id.article_img);
		TextView articleContent = (TextView)findViewById(R.id.article_content);
		TextView articleWriteDate = (TextView)findViewById(R.id.article_date);
		
		articleWriter.setText(getIntent().getExtras().getString("articleWriter"));
		articleContent.setText(getIntent().getExtras().getString("articleContent"));
		articleWriteDate.setText(getIntent().getExtras().getString("articleWriteDate"));
		try{
			InputStream is = new FileInputStream(getApplicationContext().getFilesDir().getPath() + "/" + getIntent().getExtras().getString("articleImg"));
			Drawable d = Drawable.createFromStream(is, null);
			articleImage.setImageDrawable(d);
			
		} catch(IOException e){
			Log.e("ERROR", "ERROR" + e);	
		}
		
		//댓글 보여주기
		refreshComment();
		
		//댓글 등록하기
		Button commentWriteButton = (Button)findViewById(R.id.comment_write_button);
		commentWriteButton.setOnClickListener(this);
	}
	
	private void uploadComment() {
		new Thread(){
			public void run(){
				int articleNumber = getIntent().getExtras().getInt("articleNumber");
				String commentWriter = ((EditText)findViewById(R.id.comment_writer_edit)).getText().toString();
				String comment = ((EditText)findViewById(R.id.comment_write_edit)).getText().toString();
				String commentDate = new SimpleDateFormat("yyyy-mm-dd HH:mm", Locale.KOREA).format(new Date());
		
				Comment commentForUpload = new Comment(articleNumber, commentWriter, commentDate, comment);
		
				ProxyUP proxyUP = new ProxyUP();
				proxyUP.uploadComment(commentForUpload);
				}
		}.start();
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//댓글 등록 버튼에 이벤트 등록
		case R.id.comment_write_button:
			new Thread(){
				public void run(){
					uploadComment();
					finish();
					startActivity(getIntent());
				}
			}.start();
			
		}
	}

	private final Handler handler = new Handler();
	public void refreshComment(){
		try{
			new Thread(){
				public void run(){
					dao = new Dao(getApplicationContext());
					Proxy proxy = new Proxy();
					String commentJson = proxy.getCommentJson();
					dao.insertCommentData(commentJson);
					
					handler.post(new Runnable(){
						public void run(){
							commentView();
						}
					});
				}
			}.start();
			
			
		}catch(Exception e){
			Log.e("test","Getting comment ERROR : " + e);
			e.printStackTrace();
		}		
	}
	
	private void commentView(){
		try{
			commentList = dao.getCommentList();

			for(int i = 0 ; i < commentList.size(); i++){
				if(commentList.get(i).getArticleNumber() == getIntent().getExtras().getInt("articleNumber")){
					commentListForEachArticle.add(commentList.get(i));
				}
			}
			ListView commentListView = (ListView)findViewById(R.id.comment_list_view);
			CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.comment_row, commentListForEachArticle);
			commentListView.setAdapter(commentAdapter);
		}catch(Exception e){
			Log.i("test","commentView ERROR :" + e);
			e.printStackTrace();
		}
	}



}
