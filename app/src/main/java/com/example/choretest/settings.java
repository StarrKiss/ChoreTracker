package com.example.choretest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settings extends AppCompatActivity {

    public Switch darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    public void exit(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void changeUser(View view){
        Intent intent = new Intent(this, userSelection.class);

        startActivity(intent);
    }

}


