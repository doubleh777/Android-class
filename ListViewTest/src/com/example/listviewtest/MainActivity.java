package com.example.listviewtest;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {


		private Button simpleListViewButton;
		private Button customListViewButton;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			simpleListViewButton = (Button)findViewById(R.id.SimpleListViewButton);
			customListViewButton = (Button)findViewById(R.id.CustomListViewButton);
			
			simpleListViewButton.setOnClickListener(this);
			customListViewButton.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			
			switch(v.getId()){
			
			case R.id.SimpleListViewButton:  // 클릭 이벤트를 받은 버튼의 id값과 같은 것을 찾아 엑티비티를 변경함.
				Intent simpleListViewIntent = new Intent(this, SimpleListViewActivity.class);
				startActivity(simpleListViewIntent);
				break;
				
			case R.id.CustomListViewButton:
				Intent customListViewIntent = new Intent(this, CustomListViewActivity.class);
				startActivity(customListViewIntent);
				break;
			
			}

			
		}
	}
	
