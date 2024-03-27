package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;

import android.content.Intent;
import android.os.Bundle;
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000); // 2 giây
    }
}
