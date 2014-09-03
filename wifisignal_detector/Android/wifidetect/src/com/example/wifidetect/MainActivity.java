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
import android.widget.EditText;
import android.widget.TextView;
 

public class MainActivity extends Activity {
    
	/*private static final String TAG = "WiFiDemo";
    WifiManager wifi;
    BroadcastReceiver receiver;*/

    TextView textStatus;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup UIs
        textStatus = (TextView) findViewById(R.id.textStatus); 
        
        
        
      /*  Intent intent1 = new Intent(MainActivity.this, Locate.class);
        // EditText et = (EditText) findViewById(R.id.editText1);
     	//intent.putExtra("bcd", et.getText().toString());
         startActivity(intent1);
         finish();*/

        
        // Setup WiFi
       /* wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

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
		}*/

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
      /*  int i=0;
       // Register Broadcast Receiver
        if (receiver == null)
            receiver = new WiFiScanReceiver(this);
        
        
        
        

        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");*/
    }
    public void train(View view)
    {
    	Intent intent = new Intent(MainActivity.this, Train.class);
        EditText et = (EditText) findViewById(R.id.editText1);
    	intent.putExtra("bcd", et.getText().toString());
        startActivity(intent);
        finish();
    }
    
    public void locate(View v)
    {
    	Intent intent1 = new Intent(MainActivity.this, TabSwipe.class);
       // EditText et = (EditText) findViewById(R.id.editText1);
    	//intent.putExtra("bcd", et.getText().toString());
        startActivity(intent1);
        finish();
    }
    
    
    
    
    
    


}

