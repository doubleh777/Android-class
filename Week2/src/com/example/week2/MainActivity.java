package com.example.week2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private Button mButtonSimpleList1;
	private Button mButtonSimpleList2;
	private Button mButtonCustomList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButtonSimpleList1  = (Button)findViewById(R.id.main_button_simple_list1);
		mButtonSimpleList2  = (Button)findViewById(R.id.main_button_simple_list2);
		mButtonCustomList  = (Button)findViewById(R.id.);
		
		mButtonSimpleList1.setOnClickListener(this);
		mButtonSimpleList2.setOnClickListener(this);
		mButtonCustomList.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){

		
		}
		
	}
}
