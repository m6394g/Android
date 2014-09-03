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
 

public class Train extends Activity {
    
	private static final String TAG = "WiFiDemo";
    WifiManager wifi;
    BroadcastReceiver receiver;
    public static String tv1;
    TextView textStatus;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        // Setup UIs
        textStatus = (TextView) findViewById(R.id.textStatus);    
        
        
        Intent in = getIntent();
        tv1= in.getExtras().getString("bcd");
        
      //  Toast.makeText(getApplicationContext(), tv1, Toast.LENGTH_SHORT).show();
        
        // Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

    	//Enable WiFi 
        if (wifi.isWifiEnabled())
        	if (wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLING)
        		wifi.setWifiEnabled(true);
    	
        // Get WiFi status
        WifiInfo info = wifi.getConnectionInfo();
       // textStatus.append("\n\nWiFi Status: " + info.toString());
        try {
			textStatus.append("\n\n" + info.toString());
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
        if (receiver == null)
            receiver = new WiFiScanReceiver(this);
        
        
        
        
        registerReceiver(receiver, new IntentFilter(
        		WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        
        
        
        
        
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
    	unregisterReceiver(receiver);
        
            
    }
   /* @Override
    public void onStop()
    {
    	unregisterReceiver(receiver);
    	super.onStop();
    }*/

}


