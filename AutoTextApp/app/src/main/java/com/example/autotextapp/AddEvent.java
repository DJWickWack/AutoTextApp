package com.example.autotextapp;

import android.app.AlarmManager;
import android.content.Context;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {

    static int idCount = 0;

    static String name, contact, message, date, time;

    Button btnDatePicker, btnTimePicker, btnSubmit;
    EditText txtEventName, txtMessage, txtDate, txtTime, txtNumber;
    Switch notifyOnSend;
    public String repeatText, eventName, platform;
    private int mYear, mMonth, mDay, mHour, mMinute, dayOfWeek;
    public boolean notifyOn = false;
    Calendar calendar;

    AlarmManagerBroadcastReceiver alarm;
    private PendingIntent alarmIntent;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //set the path and database name
        String path="/data/data/"+getPackageName()+"/sample.db";
        db= SQLiteDatabase.openOrCreateDatabase(path,null);

        Spinner spinner = (Spinner) findViewById(R.id.repeat_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
                repeatText = selectedItemText;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            public String getRepeatText() {
                return repeatText;
            }
        });

        txtEventName=(EditText)findViewById(R.id.txtEventName);
        txtNumber=(EditText)findViewById(R.id.txtNumber);
        txtMessage=(EditText)findViewById(R.id.addMessage);
        btnDatePicker=(Button)findViewById(R.id.btnStartDate);
        btnTimePicker=(Button)findViewById(R.id.btnStartTime);
        txtDate=(EditText)findViewById(R.id.txtStartDate);
        txtTime=(EditText)findViewById(R.id.txtStartTime);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        notifyOnSend=(Switch)findViewById(R.id.swNotifySent);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        Submit();

        alarm = new AlarmManagerBroadcastReceiver();
    }

    @Override
    public void onClick(View v) {
        if(v == txtEventName){
            eventName = txtEventName.getText().toString();
        }

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(minute >= 10) {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                            else if( minute<10){
                                txtTime.setText(hourOfDay+":0" + minute);
                            }

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == notifyOnSend){
            if (notifyOn == false){
                notifyOn = true;
            }
            else{
                notifyOn = false;
            }
        }
    }

    public void Submit(){

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = "Joe";
                contact = txtNumber.getText().toString();
                message = txtMessage.getText().toString();
                date = txtDate.getText().toString();
                time = txtTime.getText().toString();
                dayOfWeek = (mDay + mMonth + mYear + (mYear/4) +(mYear/100)+1)%7;

                ContentValues value2= new ContentValues();
                Log.d("Tag1", "name: " + name + " message: " + " date : " + date + " Time: " + time + " Pattern: " + repeatText +" Number: " + contact);

                value2.put("name",name);
                value2.put("message",message);
                value2.put("contact",contact);
                value2.put("date", date);
                value2.put("time", time);
                value2.put("recurrsionPattern", repeatText);

                db.insert("info",null,value2);
                Log.d("Tag", "1");
                db.close();

                Calendar send = Calendar.getInstance();
                send.set(mYear, mMonth, mDay, mHour, mMinute);
                long sendTime = send.getTimeInMillis();

                if(repeatText=="Never"){
                    Context context = AddEvent.this;
                    if(alarm != null){
                        alarm.setOnetimeTimer(context, sendTime, contact, message);
                    }
                }

                //Log.d("Tag", "2");

                Intent intent = new Intent(AddEvent.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}