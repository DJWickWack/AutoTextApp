package com.example.autotextapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int SEND_SMS_PERMISSION_REQUEST_CODE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
            Boolean isAnswered = false;

            while(!isAnswered){

                if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("isAnswered", String.valueOf(isAnswered));
                }
                else{
                    isAnswered = true;
                    Log.d("isAnswered", String.valueOf(isAnswered));
                    SendMessageService sendMessageService = new SendMessageService();
                    Log.d("test:", "still testing");
                    //SmsManager smsManager = SmsManager.getDefault();
                    //smsManager.sendTextMessage("+19786978693", null, "Hi2", null, null);
                }
            }
        }
        else {
            //SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage("+19786978693", null, "Hi1", null, null);
            Log.d("test2:", "testing");
            startService(new Intent(this, SendMessageService.class));
            Log.d("test3:", " still testing");
        }

    }
}
