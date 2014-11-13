package com.example.nextagram;

import java.io.IOException;
import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class ArticleViewerActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_viewer);
		
		TextView articleWriter = (TextView)findViewById(R.id.article_writer);
		ImageView articleImage = (ImageView)findViewById(R.id.article_image);
		TextView articleBody = (TextView)findViewById(R.id.article_body);
		
		articleWriter.setText(getIntent().getExtras().getString("articleWriter"));
		articleBody.setText(getIntent().getExtras().getString("articleBody"));
		
		try{
			InputStream is = getApplicationContext().getAssets().open(getIntent().getExtras().getString("articleImageName"));
			Drawable d = Drawable.createFromStream(is, null);
			articleImage.setImageDrawable(d);
			
		} catch(IOException e){
			Log.e("ERROR", "ERROR" + e);	
		}
		
	}

}
