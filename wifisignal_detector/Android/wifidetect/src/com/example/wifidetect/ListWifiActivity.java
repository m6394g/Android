package com.example.wifidetect;

import java.util.ArrayList;

import android.os.Bundle;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;


public class ListWifiActivity extends Activity {

	SqlHandler sqlHandler;
	 ListView lvCustomList;
	 //EditText etName, etPhone;
	 Button btnsubmit;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	 
	  setContentView(R.layout.activity_list_wifi);
	  
	//  lvCustomList = (ListView) findViewById(R.id.lv_custom_list);
	  
	  
	  Bundle b=this.getIntent().getExtras();
	   String[] array=b.getStringArray("abc");
	   int[] strong = b.getIntArray("strength");
	   String Room = b.getString("room");
	  
	  //etName = (EditText) findViewById(R.id.et_name);
	  //etPhone = (EditText) findViewById(R.id.et_phone);
	 // btnsubmit = (Button) findViewById(R.id.btn_submit);
	  sqlHandler = new SqlHandler(this);
	 // showList();
	 
	  for(int i=0;i<array.length;i++)
	   {
		   
		  String query = "INSERT INTO WIFI_LIST(ROOM,WNAME,STRENGTH) " +
		    		"values ('"
		      + Room + "','" + array[i] + "', ' " +strong[i]+ " ')";
		  sqlHandler.executeQuery(query);
		  
	   }
	  
	  showList();
	 
	 }
	 
	 private void showList() {
	 
	  ArrayList<WifiListItems> wifiList = new ArrayList<WifiListItems>();
	  wifiList.clear();
	  String query = "SELECT * FROM WIFI_LIST ";
	  
	  String Data = "";
	  
	  Cursor c1 = sqlHandler.selectQuery(query);
	  if (c1 != null && c1.getCount() != 0) {
	   if (c1.moveToFirst()) {
	    do {
	    	
	    	String Room_Name = c1.getString(c1.getColumnIndex("ROOM"));
	        String Wifi_Name = c1.getString(c1.getColumnIndex("WNAME"));
	        int Strength = c1.getInt(c1.getColumnIndex("STRENGTH"));
	        Data = Data + Room_Name + "/" + Wifi_Name + "/" + Strength +"\n";
	    	
	    	
	   /*  WifiListItems wifiListItems = new WifiListItems();
	 
	     wifiListItems.setroom(c1.getString(c1
	       .getColumnIndex("ROOM")));
	     wifiListItems.setwname(c1.getString(c1
	       .getColumnIndex("WNAME")));
	     wifiListItems.setstrength(c1.getInt(c1
	       .getColumnIndex("STRENGTH")));
	     wifiList.add(wifiListItems);*/
	 
	    } while (c1.moveToNext());
	   }
	   
	  }
	  
	  c1.close();
	  TextView tv = (TextView) findViewById(R.id.textView1);
	   tv.setText(Data);
	//   setContentView(tv);
	   
	 
	  
	/*  WifiListAdapter wifiListAdapter = new WifiListAdapter(ListWifiActivity.this, R.layout.wifi_list_row, wifiList);
	  lvCustomList.setAdapter(wifiListAdapter);*/
	 
	 }
	 
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu)
	 {
		 super.onCreateOptionsMenu(menu);
		 getMenuInflater().inflate(R.menu.list_wifi,menu);
		 return true;
	 }
	 
	 
}