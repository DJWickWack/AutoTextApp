package com.example.autotextapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    //Intent intent;
    @Override
    public void onReceive(Context context, Intent intent) {
        //mp=MediaPlayer.create(context, R.raw.alarm);
        //mp.start();
        Bundle bundle = intent.getExtras();
        String number = bundle.getString("number");
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, "Hi", null, null);
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}