package com.example.wifidetect;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDbHelper extends SQLiteOpenHelper {

	public static final String DATABASE_TABLE = "WIFI_LIST";
	 
	 public static final String COLUMN1 = "ROOM";
	 public static final String COLUMN2 = "WNAME";
	 public static final String COLUMN3 = "STRENGTH";
	 private static final String SCRIPT_CREATE_DATABASE = "create table "
	   + DATABASE_TABLE + " (" + COLUMN1 + " , " + COLUMN2
	   + " , " + COLUMN3 + " INTEGER );";
	 
	 public SqlDbHelper(Context context, String name, CursorFactory factory,
	   int version) {
	  super(context, name, factory, version);
	  // TODO Auto-generated constructor stub
	 
	 }
	 
	 @Override
	 public void onCreate(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
	  db.execSQL(SCRIPT_CREATE_DATABASE);
	 
	 }
	 
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  // TODO Auto-generated method stub
	  db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	  onCreate(db);
	 }

}