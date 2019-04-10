package com.example.autotextapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;

import static com.example.autotextapp.AddEvent.contact;
import static com.example.autotextapp.AddEvent.endDate;
import static com.example.autotextapp.AddEvent.endTime;
import static com.example.autotextapp.AddEvent.message;
import static com.example.autotextapp.AddEvent.startDate;
import static com.example.autotextapp.AddEvent.startTime;
import static com.example.autotextapp.AddEvent.idCount;


public class MainActivity extends AppCompatActivity{

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the path and database name
        String path="/data/data/"+getPackageName()+"/sample.db";

       db= SQLiteDatabase.openOrCreateDatabase(path,null);
       String sql = "CREATE TABLE IF NOT EXISTS info "+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,message TEXT,contact TEXT, startdate TEXT,enddate TEXT, starttime TEXT, endtime TEXT );";
       db.execSQL(sql);

        ContentValues values= new ContentValues();
        values.put("name","Bruce");
        values.put("message","hey");
        values.put("contact","bobby");
        values.put("startdate","4/8/19");
        values.put("enddate","4/8/19");
        values.put("starttime","8:00 am");
        values.put("endtime","9:00 pm");

        db.insert("info",null,values);

        idCount++;

        //Drop the Table
        //db.execSQL("DROP TABLE IF EXISTS info");

        db.close();

        Button btnMain = (Button)findViewById(R.id.button);


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =
                        new Intent(MainActivity.this, AddEvent.class);

                startActivity(intent);


            }
        });


    }



}//end of Main
