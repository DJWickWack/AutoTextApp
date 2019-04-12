package com.example.autotextapp;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ListItemAdapter extends ArrayAdapter<ListItem> {

    private LayoutInflater mInflater;

    public ListItemAdapter(Context context, int rid, List<ListItem> list) {
        super(context, rid, list);
        mInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ListItem item = (ListItem)getItem (position);

        View view = mInflater.inflate(R.layout.list_item, null);

         //String contactName;
        TextView contactName;
        contactName = (TextView)view.findViewById(R.id.contactName);
        contactName.setText(item.contactName);
         //Bitmap messengerIcon;

        ImageView messengerIcon;
       // messengerIcon = (ImageView)view.findViewById(R.id.messengerIcon);
       // messengerIcon.setImageBitmap(item.messengerIcon);

         //Date messageSendDate;
        TextView messageSendDate;
        messageSendDate = (TextView)view.findViewById(R.id.messageSendDate);
        messageSendDate.setText(item.messageSendDate.toString());

         //Time sendTime;

        TextView sendTime;
        sendTime = (TextView)view.findViewById((R.id.sendTime));
        sendTime.setText(item.sendTime.toString());
        
         //String message;
        TextView message;
        sendTime = (TextView)view.findViewById((R.id.message));
        sendTime.setText(item.message);



        return view;
    }
}
