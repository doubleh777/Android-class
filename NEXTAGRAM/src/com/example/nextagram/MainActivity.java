package com.example.nextagram;

import java.util.ArrayList;








import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener{

	private Button writeButton;
	private Button refreshButton;
	private ArrayList<Article> articleList;
	Dao dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		writeButton = (Button)findViewById(R.id.write_button);
		refreshButton = (Button)findViewById(R.id.refresh_button);
		
		writeButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		
		
		refreshData();
		
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.write_button:
			Intent writeIntent = new Intent(this, WriteArticleActivity.class);
			startActivity(writeIntent);
			break;
			
		case R.id.refresh_button:
			refreshData();
			break;
		}
	}
		

	
	private void listView(){
		articleList = dao.getArticleList();
		//DATA를 생성해서 ArrayList에 넣었으니 Adapter에 넣으면 끝이네!
		//그런데 Custom한 ListView이기때문에 CustomAdapter를 만들어야겠군.
		
		ListView listView = (ListView)findViewById(R.id.main_list_view);
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
		
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);
	}
	
	private final Handler handler = new Handler();
	public void refreshData(){
		new Thread(){
			public void run(){
				dao = new Dao(getApplicationContext());
				Proxy proxy = new Proxy();
				String jsonData = proxy.getJSON();
				dao.insertJasonData(jsonData);
				
				handler.post(new Runnable(){
					public void run(){
						listView();
					}
				});
			}
		}.start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent articleViewerIntent = new Intent(this, ArticleViewerActivity.class);
		articleViewerIntent.putExtra("articleWriter", articleList.get(position).getWriter());
		articleViewerIntent.putExtra("articleContent", articleList.get(position).getContent());
		articleViewerIntent.putExtra("articleImg", articleList.get(position).getImgName());
		articleViewerIntent.putExtra("articleWriteDate", articleList.get(position).getWriteDate());
		articleViewerIntent.putExtra("articleNumber", articleList.get(position).getArticleNumber());
		startActivity(articleViewerIntent);
	}


}
