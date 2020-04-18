package com.hemilioaraujo.massivesms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button send;
    EditText number, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button)findViewById(R.id.btnSend);
        number = findViewById(R.id.txtNumber);
        message = findViewById(R.id.txtMessage);

        if(ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{

        };

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(number.getText().toString(),message.getText().toString());
            }
        });
    }

    private void sendMessage (String number, String message){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number,null,message,null,null);
            Toast.makeText(getApplicationContext(), "SMS Sent!.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS not sent, incorrect data.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}