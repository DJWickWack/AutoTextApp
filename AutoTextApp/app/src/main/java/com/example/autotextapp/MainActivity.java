package com.example.autotextapp;

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
        values.put("names","Bruce");
        db.insert("people",null,values);
    }
}
