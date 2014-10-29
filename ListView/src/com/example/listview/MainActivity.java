package com.example.listview;

import com.example.listview.CustomListActivity;
import com.example.listview.SimpleList1Activity;
import com.example.listview.SimpleList2Activity;
import com.example.listview.R;
import com.example.listview.R.id;
import com.example.listview.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements OnClickListener
{
	private Button mButtonSimpleList1;
	private Button mButtonSimpleList2;
	private Button mButtonCustomList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mButtonSimpleList1 = (Button)findViewById(R.id.simple_list1);
        mButtonSimpleList2 = (Button)findViewById(R.id.simple_list2);
        mButtonCustomList = (Button)findViewById (R.id.customList);
        
        mButtonSimpleList1.setOnClickListener(this);
        mButtonSimpleList2.setOnClickListener(this);
        mButtonCustomList.setOnClickListener(this);
        
    }



 }
