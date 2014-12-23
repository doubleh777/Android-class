package com.example.nextagram;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.MediaStore.Images;
import android.provider.Settings.Secure;

public class WriteArticleActivity extends ActionBarActivity implements OnClickListener{

	private EditText editWriter;
	private EditText editTitle;
	private EditText editContent;
	private ImageButton addImageButton;
	private Button uploadButton;
	private ProgressDialog progressDialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_article);
		
		editWriter = (EditText)findViewById(R.id.edit_writer);
		editTitle = (EditText)findViewById(R.id.edit_title);
		editContent = (EditText)findViewById(R.id.edit_content);
		
		addImageButton = (ImageButton)findViewById(R.id.add_image_button);
		addImageButton.setOnClickListener(this);
		uploadButton = (Button)findViewById(R.id.upload_button);
		uploadButton.setOnClickListener(this);
	}
	
	private String filePath;
	private String fileName;
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		try{ // 사진 선택 도중에 취소를 할 경우 null값이 리턴될 수 있으므로 그러한 상황을 방지하기 위한 예외처리
			if(requestCode == REQUEST_PHOTO_ALBUM){
				Uri uri = getRealPathUri(data.getData());
				filePath = uri.toString();
				fileName = uri.getLastPathSegment();
			
				Bitmap bitmap = BitmapFactory.decodeFile(filePath); //BitmapFactory의 메소드에는 사진의 사이즈 등을 조절 할 수 있는 것들이 있다.
				addImageButton.setImageBitmap(bitmap); //너무 큰 용량의 이미지 안뜰 수 도 있다..
														//따라서 이미지 최적화가 필요함..
			}
		} catch(Exception e){
			Log.e("test","OnActivityResult ERROR: " + e);
			e.printStackTrace();
		}
	}
	
	private Uri getRealPathUri(Uri uri) {
		Uri filePathUri = uri;
		if (uri.getScheme().toString().compareTo("content") == 0) {
			Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null,null, null);
			if (cursor.moveToFirst()) {
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				filePathUri = Uri.parse(cursor.getString(column_index));
			}
		}
		return filePathUri;
	}
	
	private static final int REQUEST_PHOTO_ALBUM = 1;
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.add_image_button:
			Intent intent = new Intent(Intent.ACTION_PICK); //ACTION_PICK은 겔러리앱, 사진앱 등을 선택하도록 띄워주는것
			intent.setType(Images.Media.CONTENT_TYPE);
			intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, REQUEST_PHOTO_ALBUM);
			break;
		
		case R.id.upload_button:
			
			final Handler handler = new Handler();
			
				new Thread(){
					public void run(){
						
						handler.post(new Runnable() {
							public void run(){
								progressDialog = ProgressDialog.show(WriteArticleActivity.this, "","업로드 중입니다.");
							}
						});
						
						String ID = Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
						String DATE = new SimpleDateFormat("yyyy-mm-dd HH:mm", Locale.KOREA).format(new Date());
						
						Article article = new Article(
								0,
								editTitle.getText().toString(),
								editWriter.getText().toString(),
								ID,
								editContent.getText().toString(),
								DATE,
								fileName);
					
						ProxyUP proxyUP = new ProxyUP();
						proxyUP.uploadArticle(article, filePath);
						
						handler.post(new Runnable() {
							public void run(){
								progressDialog.cancel();
								
								finish();
							}
						});
						
					}
					
				}.start();
			break;
		}
	}

	

}
