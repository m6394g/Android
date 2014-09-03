package com.example.wifidetect;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 

public class Locate extends Activity {
    
	private static final String TAG = "WiFiDemo1";
    WifiManager wifi1;
    BroadcastReceiver receiver1;
  //  public static String tv1;
    TextView textStatus1;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        // Setup UIsWiFiDemo1
        textStatus1 = (TextView) findViewById(R.id.textStatus);    
        
        
       /* Intent in = getIntent();
        tv1= in.getExtras().getString("bcd");*/
        
      //  Toast.makeText(getApplicationContext(), tv1, Toast.LENGTH_SHORT).show();
        
        // Setup WiFi
        wifi1 = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        if(wifi1.isWifiEnabled())
        {
        	wifi1.setWifiEnabled(false);
        }

    	//Enable WiFi 
        /*if (wifi1.isWifiEnabled())
        	if (wifi1.getWifiState() != WifiManager.WIFI_STATE_ENABLING)
        		wifi1.setWifiEnabled(true);*/
        wifi1.setWifiEnabled(true);
    	
        // Get WiFi status
        WifiInfo info1 = wifi1.getConnectionInfo();
       // textStatus.append("\n\nWiFi Status: " + info.toString());
        try {
			textStatus1.append("\n\n" + info1.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 /*       // List available networks
        WifiConfiguration config= new WifiConfiguration();
        config.SSID="SSID_OF_NETOWRK";
        config.allowedKeyManagement.set(KeyMgmt.NONE);	
        config.status=WifiConfiguration.Status.ENABLED;
        //int netId=wifi.addNetwork(config);
        try {
			textStatus.append("\n\n" + config.toString() + "\n\n");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        wifi.saveConfiguration();
        wifi.reconnect();
 */       
             
 /*       List<WifiConfiguration> configs = wifi.getConfiguredNetworks();
        if (configs != null)
        {
        for (WifiConfiguration config : configs) {
            try {
				textStatus.append("\n\n" + config.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        }*/
       // Register Broadcast Receiver
     /*   if (receiver1 == null)
            receiver1 = new WiFiScanReceiver_Locate(this);
        
        
        
        
        registerReceiver(receiver1, new IntentFilter(
        		WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));*/
        
        
        
      /*  Intent intent = new Intent(Train.this, WiFiScanReceiver.class);
        intent.putExtra("pass", tv1);*/
     //  sendBroadcast(intent);
        Log.d(TAG, "onCreate()");
        
        
      /*  Intent ine=new Intent(Train.this, Database.class);
   	 	ine.putExtra("rn", tv1);
   	 	startActivity(ine);*/
    }
    
    
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        unregisterReceiver(receiver1);
            
    }

    /*@Override
    public void onDestroy() {
        unregisterReceiver(receiver1);
            super.onDestroy();
    }*/
   /* public void onStop()
    {
    	unregisterReceiver(receiver);
    	super.onStop();
    }*/

}


