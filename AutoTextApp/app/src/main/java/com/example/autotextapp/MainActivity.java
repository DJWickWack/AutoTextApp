package com.example.autotextapp;

import android.R;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the path and database name
        String path="/data/data/"+getPackageName()+"/sample.db";

        SQLiteDatabase db;
        db= SQLiteDatabase.openOrCreateDatabase(path,null);
        String sql = "CREATE TABLE IF NOT EXISTS info"+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,message TEXT,contact TEXT,platform TEXT, startdate TEXT,enddate TEXT, starttime TEXT, endtime TEXT );";
        db.execSQL(sql);

        ContentValues values= new ContentValues();
        values.put("name","Bruce");
        values.put("message","hey");
        values.put("contact","bobby");
        values.put("platform","SMS");
        values.put("startdate","4/8/19");
        values.put("enddate","4/8/19");
        values.put("starttime","8:00 am");
        values.put("endtime","9:00 pm");

        db.insert("info",null,values);
        ContentValues value2= new ContentValues();
        value2.put("name","Bobby");
        value2.put("message","Hi there me friend");
        value2.put("contact","Jeff");
        value2.put("platform","FB");
        value2.put("startdate","4/15/19");
        value2.put("enddate","4/16/19");
        value2.put("starttime","8:00 am");
        value2.put("endtime","12:00 pm");
        db.insert("info",null,value2);
    }
}
