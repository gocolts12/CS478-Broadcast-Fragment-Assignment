package com.example.appa1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        Log.i("Companion receiver", "App A2 has been called into action!");

        //Intent webPageIntent = new Intent("com.example.appa1.WebpageActivity");
        //StartActivity(webPageIntent);
    }
}