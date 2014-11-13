package com.example.listviewtest;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SimpleListViewActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_list_view);
		
		ListView listView = (ListView)findViewById(R.id.SimpleListView); //일단 simpleListView를 변수에 담아놓고, 
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		arrayList.add("데이터1");  //데이터를 가진 ArrayList를 만들고!!
		arrayList.add("데이터2");
		arrayList.add("데이터3");
		arrayList.add("데이터4");
		arrayList.add("데이터5");
		
		ArrayAdapter<String> simpleAdapter;
		simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
		// simple_list_item_1은 SDK가 기본적으로 제공하는 한줄짜리 리스트 레이아웃.
		
		listView.setAdapter(simpleAdapter);
		//listView.setAdapter를 통해서 어뎁터를 적용시킴.
	}

}
