package com.example.wifidetect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.Toast;
import com.example.wifidetect.Globals;

public class WiFiScanReceiver_Locate extends BroadcastReceiver {
	ListWifiActivity_Locate wifiDemo1;
	//String str;

	public WiFiScanReceiver_Locate(ListWifiActivity_Locate wifiDemo1) {
		super();
		this.wifiDemo1 = wifiDemo1;
	//	str = wifiDemo.tv1;
	//	Log.d("pinky ", str);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// String Room_Name = intent.getStringExtra("pass");
		// Toast.makeText(context, Room_Name, Toast.LENGTH_LONG).show();

		List<ScanResult> results1 = wifiDemo1.wifi1.getScanResults();
		// ArrayList<WifiConnectionBean> al = new
		// ArrayList<WifiConnectionBean>();
		//String[] data1;
		//int[] lvl1;
		int i = 0;
		Globals.data1 = new String[results1.size()];
		Globals.lvl1 = new int[results1.size()];
		ScanResult bestSignal1 = null;
		if (results1 != null) {
			for (ScanResult result : results1) {
				Globals.data1[i] = result.SSID;
				Globals.lvl1[i] = result.level;
				i = i + 1;
				/*
				 * Log.d("this is my array", "arr: " + Arrays.toString(data));
				 * // or System.out.println("arr: " + Arrays.toString(data));
				 */
				String message = String.format("%s  %s ", result.SSID,
						result.level);
				/*
				 * Intent in = new Intent(context,Database.class);
				 * in.putExtras();
				 */

				// al.add(message);

				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				Log.d("Debug", "onReceive() message: " + message);
				if (bestSignal1 == null
						|| WifiManager.compareSignalLevel(bestSignal1.level,
								result.level) < 0)
					bestSignal1 = result;
			}
			Globals.num = i;
			/*
			 * Intent in = new Intent( context,Database.class );
			 * in.putExtra("abc", data); context.startActivity(in);
			 */

			/*
			 * Bundle b=new Bundle(); b.putStringArray("key", data); Intent
			 * in=new Intent(context, Database.class); in.putExtras(b);
			 * context.startActivity(in);
			 */

			/*Intent in = new Intent(context, ListWifiActivity_Locate.class);
			in.putExtra("abc", data1);
			in.putExtra("strength", lvl1);
			//in.putExtra("room",str);
			context.startActivity(in);
			((Activity)(context)).finish();*/


			/*
			 * for(String x: data) { Log.d("tag","x:"+x); }
			 */

			/*
			 * for(int j=0;j<i;j++) { String message = String.format(
			 * "networks found %s. Strongest is %s having strength %s "
			 * ,results.size(), bestSignal.SSID ,bestSignal.level);
			 * Toast.makeText(context, data[j], Toast.LENGTH_LONG).show();
			 * Log.d("Debug", "onReceive() message: " + message); //Log.d("hhh",
			 * "hey "+ al.get(j)); }
			 */
			/*
			 * for(int j=0; j < results.size(); j++){ System.out.println("SSID"
			 * + data[j] + " " + "Strength" + lvl[j] + "\n"); }
			 */

			// Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			// Log.d("Debug", "onReceive() message: " + message);
		}
	}
}
