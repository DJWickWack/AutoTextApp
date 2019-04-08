package com.example.autotextapp;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        ListItemAdapter adapter;
        adapter = new ListItemAdapter(this, 0, list);
        // Assign ListItemAdapter to ListView
        ListView listView = (ListView)findViewById(R.id.EventList);
        listView.setAdapter(adapter);

        //set the path and database name


        ContentValues values= new ContentValues();
        values.put("name","Bruce");
        values.put("message","hey");
        values.put("contact","bobby");
        values.put("platform","SMS");
        values.put("date","4/8/19");
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
        Log.d("Tag", "Gets to 1");

        while(iter.moveToNext()){
            Log.d("Tag", "Gets to 2");
            ListItem nextItem = new ListItem();
            Log.d("Tag", "Gets to 3");
            nextItem.contactName = iter.getString(0);
            Log.d("Tag", "Gets to 4");
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            Log.d("Tag", "Gets to 5");
            nextItem.messageSendDate = iter.getString(4);
            Log.d("Tag", "Gets to 6");
            nextItem.sendTime = iter.getString(5);
            Log.d("Tag", "Gets to 7");
            nextItem.message = iter.getString(1);
            Log.d("Tag", "Gets to 8");

            list.add(nextItem);
            Log.d("Tag",nextItem.toString());
        }
    }
}

