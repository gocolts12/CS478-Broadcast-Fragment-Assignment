package com.example.appa1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver1;
    private IntentFilter mFilter;
    private static final String CUSTOM_PERMISSION = "edu.uic.cs478.s19.kaboom";
    private static final String TOAST_INTENT = "edu.uic.cs478.s19.broadcast";
    private Intent i = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilter = new IntentFilter(TOAST_INTENT);
        mFilter.setPriority(10);
        mReceiver1 = new MyReceiver();
        registerReceiver(mReceiver1, mFilter);

        Button permissionButton = (Button) findViewById(R.id.button);

        permissionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, CUSTOM_PERMISSION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //If the app has the permission, register the receiver
                    //BroadcastReceiver receiverForA3 = new ForeignReceiver();

                    //After the receiver is registered, launch App A2
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.example.appa2", "com.example.appa2.MainActivity"));
                    startActivity(intent);

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{CUSTOM_PERMISSION}, 0);
                }
            }
        });

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //After the receiver is registered, launch App A2
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.example.appa2", "com.example.appa2.MainActivity"));
            startActivity(intent);


        } else {
            Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show();
        }
    }

}