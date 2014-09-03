package com.example.wifidetect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.app.Activity;


public class ListWifiActivity_Locate extends Activity implements OnSeekBarChangeListener {
	
	private static final String TAG = "WiFiDemo1";
	
	WifiManager wifi1;
	

	private SeekBar PRICEbar,DISTANCEbar, RATINGbar,PRICEbar1,DISTANCEbar1, RATINGbar1; // declare seekbar object variable
    // declare text label objects
    private TextView PRICEtextProgress,DISTANCEtextProgress, RATINGtextProgress,PRICEtextProgress1,DISTANCEtextProgress1, RATINGtextProgress1;
	SqlHandler sqlHandler;
	 ListView lvCustomList;
	 //EditText etName, etPhone;
	 Button btnsubmit;
	 String[] array;
	 int[] strong;
	 String[] rr = new String[10000];
	 String[] ww = new String[10000];
	 int[] ss = new int[10000];
	 int room_na;
	 Handler customHandler;
	 
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	 
	  
		  
		//  stopService(intent1);
		  
	 
	  setContentView(R.layout.activity_list_wifi_activity__locate);
	  
	  
	  
	  customHandler = new android.os.Handler();
      customHandler.postDelayed(updateTimerThread, 0);
	  
	  
	  
	  
	  
	  PRICEbar = (SeekBar)findViewById(R.id.PRICEseekBarID); // make seekbar object
      PRICEbar.setOnSeekBarChangeListener(this); 
      PRICEbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
              // TODO Auto-generated method stub

          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
              // TODO Auto-generated method stub

          }

          @Override
          public void onProgressChanged(SeekBar seekBar, int progress,
                  boolean fromUser) {
              // TODO Auto-generated method stub
              PRICEtextProgress = (TextView)findViewById(R.id.PRICEtextViewProgressID);
              PRICEtextProgress.setText("Temperature:: "+progress+"C");

          }
      });
      
      PRICEbar1 = (SeekBar)findViewById(R.id.PRICEseekBarID1); // make seekbar object
      PRICEbar1.setOnSeekBarChangeListener(this); 
      PRICEbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
              // TODO Auto-generated method stub

          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
              // TODO Auto-generated method stub

          }

          @Override
          public void onProgressChanged(SeekBar seekBar, int progress,
                  boolean fromUser) {
              // TODO Auto-generated method stub
              PRICEtextProgress1 = (TextView)findViewById(R.id.PRICEtextViewProgressID1);
              PRICEtextProgress1.setText("Light:: "+progress+"J");

          }
      });
      
	  
	//  lvCustomList = (ListView) findViewById(R.id.lv_custom_list);
      
      
      
      
	 /* Bundle b=this.getIntent().getExtras();
	  array=b.getStringArray("abc");
	  strong = b.getIntArray("strength");*/
      
	  // String Room = b.getString("room");
	  
	  //etName = (EditText) findViewById(R.id.et_name);
	  //etPhone = (EditText) findViewById(R.id.et_phone);
	 // btnsubmit = (Button) findViewById(R.id.btn_submit);
	  
	 // showList();
	 
	 /* for(int i=0;i<array.length;i++)
	   {
		   
		  String query = "INSERT INTO WIFI_LIST(ROOM,WNAME,STRENGTH) " +
		    		"values ('"
		      + Room + "','" + array[i] + "', ' " +strong[i]+ " ')";
		  sqlHandler.executeQuery(query);
		  
	   }*/
	  
	  
	 //stopService(intent);
	 }
	 
	 private Runnable updateTimerThread = new Runnable()
	 {
	         public void run()
	         {
	             //write here whaterver you want to repeat
	        	 
	        	 wifi1 = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	     	    
	       	  /*  if(wifi1.isWifiEnabled())
	       	    {
	       	    	wifi1.setWifiEnabled(false);
	       	    }*/
	       	    
	       	    wifi1.setWifiEnabled(true);
	       	    

	       	    
	       	    List<ScanResult> results1 = wifi1.getScanResults();

	       		int i = 0;
	       		int abc = results1.size();
	       		System.out.println(abc);
	       		array = new String[results1.size()];
	       		strong = new int[results1.size()];
	       		ScanResult bestSignal1 = null;
	       		
	       		if (results1 != null) {

	       			for (ScanResult result : results1) {
	       			    System.out.println("mohit");

	       				array[i] = result.SSID;
	       				strong[i] = result.level;
	       				i = i + 1;
	       				
	       				String message = String.format("%s  %s ", result.SSID,
	       						result.level);
	       				

	       			//	Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	       				Log.d("Debug", "onReceive() message: " + message);
	       				if (bestSignal1 == null
	       						|| WifiManager.compareSignalLevel(bestSignal1.level,
	       								result.level) < 0)
	       					bestSignal1 = result;
	       			}
	       		}
	       			//Globals.num = i;
	       	    
	       	    
	       	    
	       	   /* Intent intent1 = new Intent(this, WiFiScanReceiver_Locate.class);
	       	      this.startService(intent1);
	       		  
	       		  int ld=Globals.num;
	       		  array = new String[ld];
	       		  strong = new int[ld];
	       		  
	       		  for(int z=0;z<ld;z++)
	       		  {
	       			  array[z] = Globals.data1[z];
	       			  strong[z] = Globals.lvl1[z];
	       		  }*/
	       	  
	       		  
	       		  sqlHandler = new SqlHandler(getApplicationContext());
	       		  
	       		  showList(array,strong);
	        	 
	        	 
	        	 
	        	 
	             customHandler.postDelayed(this, 5000);
	         }
	 };
	 
	 private void showList(String []array,int []strong) {
	 
	  ArrayList<WifiListItems> wifiList = new ArrayList<WifiListItems>();
	  wifiList.clear();
	  String query = "SELECT ROOM,WNAME,AVG(STRENGTH) FROM WIFI_LIST GROUP BY ROOM,WNAME";
	//  String query = "SELECT tab1.ROOM, tab1.WNAME, tab1.AVG(STRENGTH) FROM WIFI_LIST tab1, WIFI_LIST tab2 WHERE tab1.ROOM = tab2.ROOM GROUP BY tab1.WNAME";

	//  String query = "SELECT tab1.ROOM, tab1.WNAME, tab1.AVG(STRENGTH) FROM WIFI_LIST AS tab1 WHERE tab1.ROOM = (SELECT DISTINCT tab2.ROOM FROM WIFI_LIST AS tab2) GROUP BY WNAME";
//	  String query = "SELECT ROOM FROM WIFI_LIST ";
	  
	  String[] Data;
	  Data = new String[1000];
	  int i = 0;
	  
	  String Data1 = "";
	  
	  
	  Cursor c1 = sqlHandler.selectQuery(query);https://mess.iiit.ac.in/mess/web/student_home.php
	  if (c1 != null && c1.getCount() != 0) {
	   if (c1.moveToFirst()) {
	    do {
	    	
	    	
	    	String Room_Name = c1.getString(c1.getColumnIndex("ROOM"));
	        String Wifi_Name = c1.getString(c1.getColumnIndex("WNAME"));
	        int Strength = c1.getInt(c1.getColumnIndex("AVG(STRENGTH)"));
	    //	String[] columns = c1.getColumnNames();
	        
	        rr[i] = Room_Name;
	        ww[i] = Wifi_Name;
	        ss[i] = Strength;
	        
	    //	Data1 = Data1 +  Room_Name + "/" + Wifi_Name + "/" + Strength + "/" +"\n";
	       // Data[i] = Room_Name;
	        i++;
	    	
	    	
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
	  
	  int j=0,distance,k,temp,result=1000000,s=0,t=-1;
	  room_na=0;
	  temp=array.length;
	  for(j=0;j<i;j++)
	  {
		  s=0;
		  distance=0;
		 while(rr[j].equalsIgnoreCase(rr[j+1]) && j!=i-1)
		 {
			 for(k=0;k<temp;k++)
			 {
				 if(ww[j].equalsIgnoreCase(array[k]))
				 {
					 distance=distance + ((strong[k]-ss[j])*(strong[k]-ss[j]));
					 s++;
					 break;
				 }
			 }
			 j++;
		 }
		 
		 for(k=0;k<temp;k++)
		 {
			 if(ww[j].equalsIgnoreCase(array[k]))
			 {
				 distance=distance + ((strong[k]-ss[j])*(strong[k]-ss[j]));
				 s++;
				 break;
			 }
		 }
		 
		 if(s>t)
		 {
			 t=s;
			 room_na=j;
			 
		 }
		 else if(s==t)
		 {
			 if(distance<result)
			 {
				 result = distance;
				 room_na=j;
			 }
		 }
		 
	  }
	 
	  
	  Data1 = "You are in " + rr[room_na] + ".";

	  TextView tv = (TextView) findViewById(R.id.textView1);
	  
	 /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	  params.setMargins(10,10,10,0);
	  tv.setLayoutParams(params); */
	  
	  tv.setText(Data1);
	  
	  Toast.makeText(getApplicationContext(), Data1, Toast.LENGTH_LONG).show();
	  
	/*  Intent intent = new Intent(ListWifiActivity_Locate.this, MainActivity.class);
      startActivity(intent);
      finish();*/
	  
	  //sendPostRequest(rr[room_na]);
	//   setContentView(tv);
	  
	 /* Button ok=(Button)findViewById(R.id.button4);
      ok.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              // TODO Auto-generated method stub
        	  EditText et = (EditText) findViewById(R.id.editText2);
               String Room=et.getText().toString();   
               for(int i=0;i<array.length;i++)
        	   {
        		   
        		  String query = "INSERT INTO WIFI_LIST(ROOM,WNAME,STRENGTH) " +
        		    		"values ('"
        		      + Room + "','" + array[i] + "', ' " +strong[i]+ " ')";
        		  sqlHandler.executeQuery(query);
        		  
        	   }
          }           
      });*/
	  
	 /* Intent intent = new Intent(ListWifiActivity_Locate.this, MainActivity.class);
      EditText et = (EditText) findViewById(R.id.editText2);
  	intent.putExtra("bce", et.getText().toString());
      startActivity(intent);
      finish();*/
	 
	  
	/*  WifiListAdapter wifiListAdapter = new WifiListAdapter(ListWifiActivity.this, R.layout.wifi_list_row, wifiList);
	  lvCustomList.setAdapter(wifiListAdapter);*/
	 
	 }
	 
	 public void submit(View view1)
	 {
		 sendPostRequest(rr[room_na]);

		 
         Toast.makeText(getApplicationContext(), "Your inputs have been recorded to server.", Toast.LENGTH_SHORT).show();

	 }
	 
	 public void add(View view)
	 {
		 EditText et = (EditText) findViewById(R.id.editText2);
         String Room1=et.getText().toString();
         for(int i=0;i<array.length;i++)
  	   {
  		   
  		  String query = "INSERT INTO WIFI_LIST(ROOM,WNAME,STRENGTH) " +
  		    		"values ('"
  		      + Room1 + "','" + array[i] + "', ' " +strong[i]+ " ')";
  		  sqlHandler.executeQuery(query);
  		  
  	   }
         Toast.makeText(getApplicationContext(), "Your response has been added.", Toast.LENGTH_SHORT).show();
        et.setText("");
        Intent intent = new Intent(ListWifiActivity_Locate.this, MainActivity.class);
        startActivity(intent);
      //  finish();
		 
	 }
	 
	 /*public void seek(View v1)
	    {
	    	Intent intent1 = new Intent(ListWifiActivity_Locate.this, Filters.class);
	       // EditText et = (EditText) findViewById(R.id.editText1);
	    	//intent.putExtra("bcd", et.getText().toString());
	        startActivity(intent1);
	    }*/
	 
	 private void sendPostRequest(String location) {

			class SendPostReqAsyncTask extends AsyncTask<String, Void, Void>{

				@Override
				protected Void doInBackground(String... params) {
					String paramID = params[0];
					//String paramRegID = params[1];

					System.out.println("doInBackground paramID " + paramID);

					HttpClient httpClient = new DefaultHttpClient();
					//HttpPost httpPost = new HttpPost("http://10.2.28.144:8080/");
					HttpPost httpPost = new HttpPost("http://192.168.43.119/index.php");
																			
					BasicNameValuePair IDBasicNameValuePair = new BasicNameValuePair("location", paramID);
					//BasicNameValuePair regIDBasicNameValuePair = new BasicNameValuePair("regID", paramRegID);

					List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
					nameValuePairList.add(IDBasicNameValuePair);
					

					//nameValuePairList.add(regIDBasicNameValuePair);
					try {
						// UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs. 
						// This is typically useful while sending an HTTP POST request. 
						UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

						// setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
						httpPost.setEntity(urlEncodedFormEntity);
						System.out.println("PostRequest1");


						try {
							System.out.println("PostRequest2");
							HttpResponse httpResponse = httpClient.execute(httpPost);
							System.out.println("httpResponse="+httpResponse);
							System.out.println("PostRequest");
							
						} catch (ClientProtocolException cpe) {
							System.out.println("First Exception HttpResponese :" + cpe);
							cpe.printStackTrace();
						} catch (IOException ioe) {
							System.out.println("Second Exception HttpResponse :" + ioe);
							ioe.printStackTrace();
						}

					} catch (UnsupportedEncodingException uee) {
						System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
						uee.printStackTrace();
					}
					return null;
					
				}
				
				protected void onPostExecute() {
					super.onPostExecute(null);
					
				}
			}
			SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
			sendPostReqAsyncTask.execute(rr[room_na]);
	 }
	 
	
	 @Override
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	        if (seekBar == PRICEbar)
	            PRICEtextProgress.setText("Temperature:: "+progress+"C");
	        if (seekBar == PRICEbar1)
	            PRICEtextProgress1.setText("Light:: "+progress+"J");
	    }
	    @Override
	    public void onStartTrackingTouch(SeekBar seekBar) {
	        // TODO Auto-generated method stub

	    }
	    @Override
	    public void onStopTrackingTouch(SeekBar seekBar) {
	        // TODO Auto-generated method stub

	    }
	    
	    
	    @Override
	    protected void onStop()
	    {
	    	customHandler.removeCallbacks(updateTimerThread);
	    	super.onStop();
	    }
	 
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu)
	 {
		 super.onCreateOptionsMenu(menu);
		 getMenuInflater().inflate(R.menu.list_wifi,menu);
		 return true;
	 }
	 
	 
	 
	 
	 
}





