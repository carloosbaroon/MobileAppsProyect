package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DoServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_service2);
    }


    public void createService (View view){
        MessagesIntentService.startAction(this, 5, "happy");

    }
}
