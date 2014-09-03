package com.example.wifidetect;

import com.example.wifidetect.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;


public class FirstFragment extends Fragment implements OnSeekBarChangeListener {

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
	 String temperature;
	 Handler customHandler;
	 View rootView;
	 ToggleButton tButton,tButton1;
	 String lightbutton="0",fanbutton="0";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		//super.onCreate(savedInstanceState);
		super.onCreateView(inflater, container, savedInstanceState);


		rootView = inflater.inflate(R.layout.activity_first_fragment, container, false);
		
		customHandler = new android.os.Handler();
	      customHandler.postDelayed(updateTimerThread, 0);

	      
	      Button button = (Button) rootView.findViewById(R.id.button4);
	      button.setOnClickListener(new OnClickListener() {           

	    	  @Override
	    	  public void onClick(View v) 
	    	  {
	    		 
	    			 EditText et = (EditText) rootView.findViewById(R.id.editText2);
	    	        String Room1=et.getText().toString();
	    	        for(int i=0;i<array.length;i++)
	    	 	   {
	    	 		   
	    	 		  String query = "INSERT INTO WIFI_LIST(ROOM,WNAME,STRENGTH) " +
	    	 		    		"values ('"
	    	 		      + Room1 + "','" + array[i] + "', ' " +strong[i]+ " ')";
	    	 		  sqlHandler.executeQuery(query);
	    	 		  
	    	 	   }
	    			 

	    	        Toast.makeText(getActivity().getApplicationContext(), "Your response has been added.", Toast.LENGTH_SHORT).show();
	    	       et.setText("");
	    			

	    	       Intent intent = new Intent(getActivity(), MainActivity.class);
	    			 
	    	       getActivity().startActivity(intent);
	    	     //  finish();
	    		  
	    	  }    
	    	});
	      
	      
		  PRICEbar = (SeekBar)rootView.findViewById(R.id.PRICEseekBarID); // make seekbar object
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
	              PRICEtextProgress = (TextView)rootView.findViewById(R.id.PRICEtextViewProgressID);
	              PRICEtextProgress.setText("Temperature:: "+progress+"C");
	              temperature = Integer.toString(progress);

	          }
	      });
	      
	      Button mbutton = (Button) rootView.findViewById(R.id.button5);
	      mbutton.setOnClickListener(new OnClickListener() {           

	    	  @Override
	    	  public void onClick(View v) 
	    	  {
	    		  sendPostRequest(rr[room_na],temperature);
	    	      Toast.makeText(getActivity().getApplicationContext(), "Your inputs have been recorded to server.", Toast.LENGTH_SHORT).show();

	    	  }
	    });
	      
	      
	      tButton = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
	      tButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	      @Override
	      public void onCheckedChanged(CompoundButton buttonView,
	      boolean isChecked) {

	      if(isChecked){
	      lightbutton = "1";
	      }else{
	      lightbutton = "0";
	      }

	      }
	      });
	      
	      tButton1 = (ToggleButton) rootView.findViewById(R.id.toggleButton2);
	      tButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	      @Override
	      public void onCheckedChanged(CompoundButton buttonView,
	      boolean isChecked) {

	      if(isChecked){
	      fanbutton = "1";
	      }else{
	      fanbutton = "0";
	      }

	      }
	      });
		  
	   
	      
		
		return rootView;
	}
	
	private Runnable updateTimerThread = new Runnable()
	 {
	         public void run()
	         {
	             //write here whaterver you want to repeat
	        	 
	        	  
	        	 
	        	 wifi1 = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
	     	    
	       	    if(wifi1.isWifiEnabled())
	       	    {
	       	    	wifi1.setWifiEnabled(false);
	       	    }
	       	    
	       	    if(wifi1.isWifiEnabled())
	       	    {
	       	    }
	       	    else
	       	    {
	       	    	wifi1.setWifiEnabled(true);
	       	    }
	       	    

	       	    
	       	    List<ScanResult> results1 = wifi1.getScanResults();

	       		int i = 0;
	       		int abc = results1.size();
	       		System.out.println(abc);
	       		array = new String[results1.size()];
	       		strong = new int[results1.size()];
	       		
	       		System.out.println("Array" + array.length);
	       		System.out.println("Strong" + strong.length);
	       		
	       		ScanResult bestSignal1 = null;
	       		
	       		if (results1 != null) {

	       			for (ScanResult result : results1) {
	       			    System.out.println("mohit");

	       			    System.out.println("Mac Address" + result.BSSID);
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
	       	  
	       		  
	       		  sqlHandler = new SqlHandler(getActivity().getApplicationContext());
	       		  
	       		  showList(array,strong);
	        	 
	        	 
	        	 
	        	 
	             customHandler.postDelayed(this, 5000);
	         }
	 };
	 
	 private void showList(String []array1,int []strong1) {
	 
	  ArrayList<WifiListItems> wifiList = new ArrayList<WifiListItems>();
	  wifiList.clear();
	  String query = "SELECT ROOM,WNAME,AVG(STRENGTH) FROM WIFI_LIST GROUP BY ROOM,WNAME";
	
	  
	  String[] Data;
	  Data = new String[1000];
	  int i = 0;
	  
	  String Data1 = "";
	  
	  
	  Cursor c1 = sqlHandler.selectQuery(query);
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
	        
	    
	        i++;
	    	
	    	
	   
	 
	    } while (c1.moveToNext());
	   }
	   
	  }
	  
	  c1.close();
	  
	  int j=0,distance,k,temp,result=1000000,s=0,t=-1;
	  room_na=0;
	  temp=array1.length;
	  for(j=0;j<i;j++)
	  {
		  s=0;
		  distance=0;
		 while(rr[j].equalsIgnoreCase(rr[j+1]) && j!=i-1)
		 {
			 for(k=0;k<temp;k++)
			 {
				 if(ww[j].equalsIgnoreCase(array1[k]))
				 {
					 distance=distance + ((strong1[k]-ss[j])*(strong1[k]-ss[j]));
					 s++;
					 break;
				 }
			 }
			 j++;
		 }
		 
		 for(k=0;k<temp;k++)
		 {
			 if(ww[j].equalsIgnoreCase(array1[k]))
			 {
				 distance=distance + ((strong1[k]-ss[j])*(strong1[k]-ss[j]));
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
	 
	  if(temp==0)
	  {
		  Data1 = "Getting Your Location...";
	  }
	  else
	  {
		  Data1 = "You are in " + rr[room_na] + ".";
	  }

	  TextView tv = (TextView) rootView.findViewById(R.id.textView1);
	  
	  
	  tv.setText(Data1);
	  if(temp==0)
	  {
		  Toast.makeText(getActivity().getApplicationContext(), "Getting Your Location...", Toast.LENGTH_LONG).show();
	  }
	  else
	  {
		  Toast.makeText(getActivity().getApplicationContext(), Data1, Toast.LENGTH_LONG).show();
	  }
	  
	
	 
	 }
	 
	 
	 
	 
	 private void sendPostRequest(String location, String temp) {

			class SendPostReqAsyncTask extends AsyncTask<String, Void, Void>{

				@Override
				protected Void doInBackground(String... params) {
					String paramID = params[0];
					String temp = temperature;
					//String paramRegID = params[1];

					System.out.println("doInBackground paramID " + paramID);
					System.out.println("TEMPERATURE " + temperature);

					HttpClient httpClient = new DefaultHttpClient();
					//HttpPost httpPost = new HttpPost("http://10.2.28.144:8080/");
					HttpPost httpPost = new HttpPost("http://192.168.43.119/index.php");
																			
					BasicNameValuePair IDBasicNameValuePair = new BasicNameValuePair("location", paramID);
					BasicNameValuePair regIDBasicNameValuePair = new BasicNameValuePair("temperature", temp);
					BasicNameValuePair lon = new BasicNameValuePair("light", lightbutton);
					BasicNameValuePair fon = new BasicNameValuePair("fan", fanbutton);

					List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
					nameValuePairList.add(IDBasicNameValuePair);
					nameValuePairList.add(regIDBasicNameValuePair);
					nameValuePairList.add(lon);
					nameValuePairList.add(fon);
					

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
	    public void onStop()
	    {
	    	customHandler.removeCallbacks(updateTimerThread);
	    	super.onStop();
	    }
	 
	 
	 
	
}
