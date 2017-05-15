package com.example.toey_kirati.motorbike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuBike extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bike);
    }

    public void clickmainactivity(View view) {
        Intent intent = new Intent(MenuBike.this, MainActivity.class);
        startActivity(intent);
    }

    public void clickchonorder(View view) {
        Intent intent = new Intent(MenuBike.this, ShowOrder.class);
        startActivity(intent);
    }
}

