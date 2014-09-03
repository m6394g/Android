package com.example.wifidetect;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class WifiListAdapter extends BaseAdapter {

	Context context;
	int layoutResourceId;
    ArrayList<WifiListItems> wifiList;
   // LayoutInflater inflater;
 
    public WifiListAdapter(Context context, int layoutResourceId, ArrayList<WifiListItems> list) {
    	
    		 
    		        this.layoutResourceId = layoutResourceId;
 
        this.context = context;
        wifiList = list;
        //inflater = LayoutInflater.from(this.context);
    }
 
    @Override
    public int getCount() {
 
        return wifiList.size();
    }
 
    @Override
    public Object getItem(int position) {
 
        return wifiList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
    	//return 0;
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        WifiListItems wifiListItems = wifiList.get(position);
 
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceId,null);
 
        }
        TextView tvroom = (TextView) convertView.findViewById(R.id.tv_room);
        tvroom.setText(wifiListItems.getroom());
        TextView tvwName = (TextView) convertView.findViewById(R.id.tv_wname);
        tvwName.setText(wifiListItems.getwname());
        TextView tvstrength = (TextView) convertView.findViewById(R.id.tv_strength);
        tvstrength.setText(wifiListItems.getstrength());
 
        return convertView;
    }


}