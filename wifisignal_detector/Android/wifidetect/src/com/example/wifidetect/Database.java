package com.example.wifidetect;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Database extends Activity {
 /** Called when the activity is first created. */
	
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 
 
  SQLiteDatabase myDB= null;
  String TableName = "myTable";
 
  String Data="";
 
  /* Create a Database. */
  try {
   myDB = this.openOrCreateDatabase("DatabaseName", MODE_PRIVATE, null);
 
   /* Create a Table in the Database. */
   myDB.execSQL("CREATE TABLE IF NOT EXISTS "
     + TableName
     + " (Field1 VARCHAR, Field2 VARCHAR, Field3 INT(3));");
 
   Bundle b=this.getIntent().getExtras();
   String[] array=b.getStringArray("abc");
   int[] strong = b.getIntArray("strength");
   String Room = b.getString("room");
   
  /* Intent rr = getIntent();
   String Room= rr.getExtras().getString("rn");*/
   //String Room = b.getString("rn");
   
   /* Insert data to a Table*/
   for(int i=0;i<array.length;i++)
   {
	   
   
   myDB.execSQL("INSERT INTO "
     + TableName
     + " (Field1, Field2, Field3)"
     + " VALUES ('"+Room+"', '"+array[i]+"' , "+strong[i]+");");
   }
   /*retrieve data from database */
   Cursor c = myDB.rawQuery("SELECT * FROM " + TableName , null);
   
   
 
   int Column1 = c.getColumnIndex("Field1");
   int Column2 = c.getColumnIndex("Field2");
   int Column3 = c.getColumnIndex("Field3");

   // Check if our result was valid.
   c.moveToFirst();
   if (c != null) {
    // Loop through all Results
    do {
     String Room_Name = c.getString(Column1);
     String Wifi_Name = c.getString(Column2);
     int Strength = c.getInt(Column3);
     Data = Data + Room_Name + "/" + Wifi_Name + "/" + Strength +"\n";
    }while(c.moveToNext());
   }
   TextView tv = new TextView(this);
   tv.setText(Data);
   setContentView(tv);
  // setContentView(R.layout.activity_database);
  }
  		
  catch(Exception e) {
   Log.e("Error", "Error", e);
  } finally {
   if (myDB != null)
    myDB.close();
  }
 }
 public void db(View view)
 {
 	Intent intent = new Intent(Database.this, MainActivity.class);
     startActivity(intent);
     finish();
 }

}
