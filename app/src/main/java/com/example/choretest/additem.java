package com.example.choretest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class additem extends AppCompatActivity {

    public FirebaseFirestore cFirestore;

    public DatabaseReference mDatabase;

    EditText choreText;

    EditText lengthText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        cFirestore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        choreText = (EditText)findViewById(R.id.choreText);

        lengthText = (EditText)findViewById(R.id.lengthText);

    }

    public void sendItem( View view){
        String appID;
        String description;
        int length;
        description = choreText.getText().toString();
        length = Integer.parseInt(lengthText.getText().toString());
        appID = getSaltString();
        chore chore = new chore(appID, description, length);

        mDatabase.child("choreList").child(appID).setValue(chore);
    }

     String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
