package com.example.nextagram;

import java.util.ArrayList;





import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener{

	
	private Button writeButton;
	private Button refreshButton;
	private ArrayList<ListData> listDataArray = new ArrayList<ListData>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		writeButton = (Button)findViewById(R.id.write_button);
		refreshButton = (Button)findViewById(R.id.refresh_button);
		
		writeButton.setOnClickListener(this);
		
		//DATA를 직접 생성해서 연습부터 해볼까  
		
		ListData data1 = new ListData("a", "기사제목1", "기사내용1","01.jpg");
		listDataArray.add(data1);
		ListData data2 = new ListData("b", "기사제목2", "기사내용2","02.jpg");
		listDataArray.add(data2);
		ListData data3 = new ListData("c", "기사제목3", "기사내용3","03.jpg");
		listDataArray.add(data3);
		ListData data4 = new ListData("d", "기사제목4", "기사내용4","04.jpg");
		listDataArray.add(data4);
		ListData data5 = new ListData("e", "기사제목5", "기사내용5","05.jpg");
		listDataArray.add(data5);
		ListData data6 = new ListData("f", "기사제목6", "기사내용6","06.jpg");
		listDataArray.add(data6);
		ListData data7 = new ListData("g", "기사제목7", "기사내용7","07.jpg");
		listDataArray.add(data7);
		
		//DATA를 생성해서 ArrayList에 넣었으니 Adapter에 넣으면 끝이네!
		//그런데 Custom한 ListView이기때문에 CustomAdapter를 만들어야겠군.
		
		ListView listView = (ListView)findViewById(R.id.main_list_view);
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
		
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.write_button:
			Intent writeIntent = new Intent(this, WriteArticleActivity.class);
			startActivity(writeIntent);
			break;
			
		case R.id.refresh_button:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent articleViewerIntent = new Intent(this, ArticleViewerActivity.class);
		articleViewerIntent.putExtra("articleWriter", listDataArray.get(position).getArticleWriter());
		articleViewerIntent.putExtra("articleBody", listDataArray.get(position).getArticleBody());
		articleViewerIntent.putExtra("articleImageName", listDataArray.get(position).getArticleImageName());
		startActivity(articleViewerIntent);
	}



}