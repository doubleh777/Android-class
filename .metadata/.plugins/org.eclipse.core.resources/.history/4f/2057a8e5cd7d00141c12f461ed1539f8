package com.example.nextagram;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<ListData>{  // adapter로 만드려면 ArrayAdpater 상속해주고....

	Context context;
	int layoutResourceId;
	ArrayList<ListData> listDataArray;
	
	public CustomAdapter(Context context, int layoutResourceId, ArrayList<ListData> listDataArray){
		
		super(context,layoutResourceId,listDataArray); // ArrayAdapter를 상속받았으니 상위 객체의 생성자에 이렇게 넣어줘야
													   // 내부적으로 adapter의 기능을 수행해주겠지!
		
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.listDataArray = listDataArray;
	}

	public View getView(int position, View convertView, ViewGroup parent){ // getView는 row에 Data를 집어넣어서 화면에 뿌릴 row하나를 생성하는것이다.
		//position은 ArrayList의 data번호, convertView는 이전에 만들어진 row(그래서 맨첨엔 이전에 넘어 View가 없으니깐 inflater를 이용해 잡아주는것
		//parent는 
		View row = convertView;
		
		if(row == null){
			
			LayoutInflater inflater = ((Activity) context).getLayoutInflater(); // Inflater를 얻는 방법
																				//context를 Activity로 casting하여 getLayoutInflater()메소드를 호출한다.
			row = inflater.inflate(layoutResourceId, parent, false);
		}
		
		TextView articleTitle = (TextView)row.findViewById(R.id.custom_layout_row_title); // 이전에 만든 row layout을 선택해
		TextView articleBody = (TextView)row.findViewById(R.id.custom_layout_row_body);
		ImageView articleImage = (ImageView)row.findViewById(R.id.article_image);
		
		articleTitle.setText(listDataArray.get(position).getArticleTitle());
		articleBody.setText(listDataArray.get(position).getArticleBody());
		
		try{
			InputStream is = context.getAssets().open(listDataArray.get(position).getArticleImageName());
			Drawable d = Drawable.createFromStream(is, null);
			articleImage.setImageDrawable(d);
			
		} catch(IOException e){
			Log.e("ERROR","ERROR"+ e);
		}
		
		return row;
	}
}
