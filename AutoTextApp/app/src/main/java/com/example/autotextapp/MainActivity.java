package com.example.autotextapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CalendarView mainCalendar;
    TextView selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCalendar = (CalendarView) findViewById(R.id.calendarView);
        selectedDate = (TextView) findViewById((R.id.currentDate));

        mainCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String date = (month + 1) + "/" + dayOfMonth + "/" + year;
            selectedDate.setText(date);
            }
        });
    }
}
