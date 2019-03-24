package edu.wit.mobileapp.autotextapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

    Button btnStartDatePicker, btnStartTimePicker, btnStopDatePicker, btnStopTimePicker, btnSubmit;
    EditText txtEventName, txtMessage, txtStartDate, txtStartTime, txtStopDate, txtStopTime;
    Switch notifyOnSend;
    public String repeatText, eventName, messageText;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public boolean notifyOn = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

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
        txtMessage=(EditText)findViewById(R.id.addMessage);

        btnStartDatePicker=(Button)findViewById(R.id.btnStartDate);
        btnStartTimePicker=(Button)findViewById(R.id.btnStartTime);
        btnStopDatePicker=(Button)findViewById(R.id.btnStopDate);
        btnStopTimePicker=(Button)findViewById(R.id.btnStopTime);
        txtStartDate=(EditText)findViewById(R.id.txtStartDate);
        txtStartTime=(EditText)findViewById(R.id.txtStartTime);
        txtStopDate=(EditText)findViewById(R.id.txtStopDate);
        txtStopTime=(EditText)findViewById(R.id.txtStopTime);

        btnStartDatePicker.setOnClickListener(this);
        btnStartTimePicker.setOnClickListener(this);
        btnStopDatePicker.setOnClickListener(this);
        btnStopTimePicker.setOnClickListener(this);

        notifyOnSend=(Switch)findViewById(R.id.swNotifySent);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

    }

    @Override
    public void onClick(View v) {

        if(v == txtEventName){

            eventName = txtEventName.getText().toString();

        }

        if(v == txtMessage){

            messageText = txtMessage.getText().toString();

        }

        if (v == btnStartDatePicker) {

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

                            txtStartDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if (v == btnStartTimePicker) {

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

                            txtStartTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == btnStopDatePicker) {

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

                            txtStopDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if (v == btnStopTimePicker) {

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

                            txtStopTime.setText(hourOfDay + ":" + minute);
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

        if (v == btnSubmit){

            //go somewhere

        }



    }


}
