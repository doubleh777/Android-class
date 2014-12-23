package com.example.news;

import java.net.URL;

import android.util.Log;

public class Proxy {

	public String getJSONData(){
		
		try{
			URL url = new URL("http://127.0.0.1:1212/loadData");
			
			
		} catch(Exception e){
			Log.e("test","Network Error! - " + e);
			e.printStackTrace();
		}
	}
}
