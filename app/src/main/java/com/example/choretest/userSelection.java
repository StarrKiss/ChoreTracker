package com.example.choretest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class userSelection extends AppCompatActivity {

    public Spinner spinner;

    public String userSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        spinner = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.users_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                userSelect = Long.toString(id);



            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }


        });





    }

    public void returnToMain(View view){
        Intent sendMessage = new Intent(this, MainActivity.class);

        startActivity(sendMessage);

        SharedPreferences prefs = this.getSharedPreferences("userShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        Intent mIntent = getIntent();


        editor.putString("userID", userSelect);

        editor.apply();

    }



}
