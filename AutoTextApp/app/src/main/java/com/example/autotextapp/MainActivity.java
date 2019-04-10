package com.example.autotextapp;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    CalendarView mainCalendar;
    TextView selectedDate;
    String date;
    List<ListItem> list = new ArrayList<ListItem>();
    SQLiteDatabase db;
    ListItemAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path="/data/data/"+getPackageName()+"/sample.db";


        db= SQLiteDatabase.openOrCreateDatabase(path,null);
        String sql = "CREATE TABLE IF NOT EXISTS info"+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,message TEXT,contact TEXT,platform TEXT, date TEXT, time TEXT);";
        db.execSQL(sql);

        mainCalendar = (CalendarView) findViewById(R.id.calendarView);
        selectedDate = (TextView) findViewById((R.id.currentDate));

        mainCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = (month + 1) + "/" + dayOfMonth + "/" + year;
                PopulateList(db);
            selectedDate.setText(date);

            }
        });


        //adapter = new ListItemAdapter(this, 0, list);

        // Assign ListItemAdapter to ListView
        listView = (ListView)findViewById(R.id.EventList);


        //set the path and database name


        ContentValues values= new ContentValues();
        values.put("name","Bruce");
        values.put("message","hey");
        values.put("contact","bobby");
        values.put("platform","SMS");
        values.put("date","4/8/2019");
        values.put("time","8:00 am");
        db.insert("info",null,values);

        ContentValues value2= new ContentValues();
        value2.put("name","Bobby");
        value2.put("message","Hi there me friend");
        value2.put("contact","Jeff");
        value2.put("platform","FB");
        value2.put("date","4/15/2019");
        value2.put("time","8:00 am");
        db.insert("info",null,value2);



    }

    public Cursor GetItemsOnDate(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM  info  WHERE date =" + "'"+date+"'", null);
    }

    public void PopulateList(SQLiteDatabase db){
        list.clear();
        Cursor iter = GetItemsOnDate(db);

        while(iter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = iter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = iter.getString(5);
            nextItem.sendTime = iter.getString(6);
            nextItem.message = iter.getString(2);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());
            adapter = new ListItemAdapter(this, 0, list);
        }

        listView.setAdapter(adapter);
    }

    public void AddEvent(View view){
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }
}

