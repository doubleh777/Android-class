package com.example.listviewtest;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



public class CustomListViewActivity extends ActionBarActivity implements OnItemClickListener{


	private ArrayList<ListData> listDataArray = new ArrayList<ListData>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view);
		
		ListData data1 = new ListData("1 - 첫번째 줄", "1 - 두번째 ", "01.jpg");
		listDataArray.add(data1);
		ListData data2 = new ListData("2 - 첫번째 줄", "2 - 두번째 ", "02.jpg");
		listDataArray.add(data2);
		ListData data3 = new ListData("3 - 첫번째 줄", "3 - 두번째 ", "03.jpg");
		listDataArray.add(data3);
		ListData data4 = new ListData("4 - 첫번째 줄", "4 - 두번째 ", "04.jpg");
		listDataArray.add(data4);
		ListData data5 = new ListData("5 - 첫번째 줄", "5 - 두번째 ", "05.jpg");
		listDataArray.add(data5);
		ListData data6 = new ListData("6 - 첫번째 줄", "6 - 두번째 ", "06.jpg");
		listDataArray.add(data6);
		ListData data7 = new ListData("7 - 첫번째 줄", "7 - 두번째 ", "07.jpg");
		listDataArray.add(data7);
		
		ListView listView = (ListView)findViewById(R.id.custom_list_view);
		
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
	
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position,
			long id) {

		Log.i("TEST", position + "번 리스트 선택");
		Log.i("TEST", "리스트 내용 :" + listDataArray.get(position).getText1());
	}


}
