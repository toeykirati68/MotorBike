package com.example.toey_kirati.motorbike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class YourName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_name);
    }

    public void clicklmainactivity(View view) {
        Intent intent = new Intent(YourName.this, MainActivity.class);
        startActivity(intent);
    }
}