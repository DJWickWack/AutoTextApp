package com.example.autotextapp;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Calendar;

public class SendMessageService extends IntentService {



    public SendMessageService() {
        super("SendMessageService");
        Log.d("test", "anybody out there");
        this.onHandleIntent(new Intent(""));
    }

    @Override
    public void onHandleIntent(Intent intent){

        //Do Work Here
        
    }

    private void sendText(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+19786978693", null, "Hi2", null, null);
    }
}
