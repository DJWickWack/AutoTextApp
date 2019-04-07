package com.example.autotextapp;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap defaultImage;

        defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);

        mainCalendar = (CalendarView) findViewById(R.id.calendarView);
        selectedDate = (TextView) findViewById((R.id.currentDate));

        mainCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String date = (month + 1) + "/" + dayOfMonth + "/" + year;
            selectedDate.setText(date);
            }
        });

        List<ListItem> list = new ArrayList<ListItem>();



        ListItem item1 = new ListItem();
        item1.contactName = "Robert";
        item1.messengerIcon = defaultImage;
        item1.messageSendDate = "4/2/2019";
        item1.sendTime = "5:00 pm";
        item1.message= "hi";
        list.add(item1);

        ListItem item2 = new ListItem();
        item2.contactName = "Nebile";
        item2.messengerIcon = defaultImage;
        item2.messageSendDate = "4/2/2019";
        item2.sendTime = "7:00 pm";
        item2.message= "helloooo";
        list.add(item2);

        ListItemAdapter adapter;
        adapter = new ListItemAdapter(this, 0, list);
        // Assign ListItemAdapter to ListView
        ListView listView = (ListView)findViewById(R.id.EventList);
        listView.setAdapter(adapter);

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
