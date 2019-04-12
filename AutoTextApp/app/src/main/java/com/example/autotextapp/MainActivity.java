package com.example.autotextapp;


import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.app.ListActivity;

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
    int dayOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path="/data/data/"+getPackageName()+"/sample.db";

        db= SQLiteDatabase.openOrCreateDatabase(path,null);
        String sql = "CREATE TABLE IF NOT EXISTS info"+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, message TEXT, contact TEXT, date TEXT, time TEXT, recurrsionPattern TEXT);";
        db.execSQL(sql);

        db.close();Log.d("Tag1", "Test");
        mainCalendar = (CalendarView) findViewById(R.id.calendarView);
        selectedDate = (TextView) findViewById((R.id.currentDate));

        mainCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("Tag1", "Test");
                date = (month + 1) + "-" + dayOfMonth + "-" + year;


                dayOfWeek = (dayOfMonth + month + year + (year/4) +(year/100)+1)%7;
                Log.d("Tag1", "Test");
                PopulateList(db);
                Log.d("Tag1", "Test");
            selectedDate.setText(date);

            }
        });

        //adapter = new ListItemAdapter(this, 0, list);

        // Assign ListItemAdapter to ListView
        listView = (ListView)findViewById(R.id.EventList);
        listView.setAdapter(adapter);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Edit_Event.class);
                startActivity(intent);
            }
        });*/





    }

    public Cursor GetItemsOnDate(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM  info  WHERE date =" + "'"+date+"'", null);
    }

    public Cursor GetReccurringDailyEvents(SQLiteDatabase db) {
        return db.rawQuery("SELECT * FROM info WHERE recurrsionPattern =" + "'Daily'", null);
    }

    public void PopulateList(SQLiteDatabase db){
        String path="/data/data/"+getPackageName()+"/sample.db";
        db= SQLiteDatabase.openOrCreateDatabase(path,null);
        list.clear();

        Log.d("Tag1", "Here");
        Cursor iter = GetItemsOnDate(db);
        Log.d("Tag1", "H");
        /*Cursor dailyRecurIter = GetReccurringDailyEvents(db);
        Log.d("Tag1", "E");
        Cursor weeklyRecurIter = GetReccurringWeeklyEvents(db);
        Log.d("Tag1", "R");
        Cursor monthlyRecurIter = GetReccurringMonthlyEvents(db);
        Log.d("Tag1", "E");
        Cursor yearlyRecurIter = GetReccurringYearlyEvents(db);
        Log.d("Tag1", "Q");*/
        while(iter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = iter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = iter.getString(5);
            nextItem.sendTime = iter.getString(6);
            nextItem.message = iter.getString(2);
            nextItem.id = iter.getInt(0);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());

        }
        /*while(dailyRecurIter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = dailyRecurIter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = dailyRecurIter.getString(5);
            nextItem.sendTime = dailyRecurIter.getString(6);
            nextItem.message = dailyRecurIter.getString(2);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());
        }

        while(weeklyRecurIter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = weeklyRecurIter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = weeklyRecurIter.getString(5);
            nextItem.sendTime = weeklyRecurIter.getString(6);
            nextItem.message = weeklyRecurIter.getString(2);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());
        }

        while(monthlyRecurIter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = monthlyRecurIter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = monthlyRecurIter.getString(5);
            nextItem.sendTime = monthlyRecurIter.getString(6);
            nextItem.message = monthlyRecurIter.getString(2);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());
        }

        while(yearlyRecurIter.moveToNext()){
            ListItem nextItem = new ListItem();
            nextItem.contactName = yearlyRecurIter.getString(3);
            nextItem.messengerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
            nextItem.messageSendDate = yearlyRecurIter.getString(5);
            nextItem.sendTime = yearlyRecurIter.getString(6);
            nextItem.message = yearlyRecurIter.getString(2);

            list.add(nextItem);
            Log.d("Tag", list.get(0).toString());
        }*/

        adapter = new ListItemAdapter(this, 0, list);
        listView.setAdapter(adapter);
    }

    public void AddEvent(View view){
        Intent intent = new Intent(MainActivity.this, AddEvent.class);
        startActivity(intent);
    }

    public void DeleteEvent(ListItem itemToDelete, View view){
        db.execSQL(" DELETE FROM info WHERE _ID=" + "'"+itemToDelete.id+"'");
    }

    public void ToggleView(View view){
        if(view.getVisibility() == View.GONE){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }



}

