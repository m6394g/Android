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

public class WiFiScanReceiver extends BroadcastReceiver {
	Train wifiDemo;
	String str;

	public WiFiScanReceiver(Train wifiDemo) {
		super();
		this.wifiDemo = wifiDemo;
		str = wifiDemo.tv1;
		Log.d("pinky ", str);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// String Room_Name = intent.getStringExtra("pass");
		// Toast.makeText(context, Room_Name, Toast.LENGTH_LONG).show();

		List<ScanResult> results = wifiDemo.wifi.getScanResults();
		// ArrayList<WifiConnectionBean> al = new
		// ArrayList<WifiConnectionBean>();
		String[] data;
		int[] lvl;
		int i = 0;
		data = new String[results.size()];
		lvl = new int[results.size()];
		ScanResult bestSignal = null;
		if (results != null) {
			for (ScanResult result : results) {
				data[i] = result.SSID;
				lvl[i] = result.level;
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
				if (bestSignal == null
						|| WifiManager.compareSignalLevel(bestSignal.level,
								result.level) < 0)
					bestSignal = result;
			}
			/*
			 * Intent in = new Intent( context,Database.class );
			 * in.putExtra("abc", data); context.startActivity(in);
			 */

			/*
			 * Bundle b=new Bundle(); b.putStringArray("key", data); Intent
			 * in=new Intent(context, Database.class); in.putExtras(b);
			 * context.startActivity(in);
			 */

			Intent in = new Intent(context, ListWifiActivity.class);
			in.putExtra("abc", data);
			in.putExtra("strength", lvl);
			in.putExtra("room",str);
			context.startActivity(in);
			((Activity)(context)).finish();

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
