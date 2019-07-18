package com.example.choretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class additem extends AppCompatActivity {

    public FirebaseFirestore cFirestore;


    EditText choreText;

    EditText lengthText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        cFirestore = FirebaseFirestore.getInstance();

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
        Map<String, Object> choreMap = new HashMap<>();
        choreMap.put("description", description);
        choreMap.put("length", length);

        cFirestore.collection("choreList").document(getSaltString())
                .set(choreMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Context context = getApplicationContext();

                        CharSequence text = "Chore added sucessfully!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast.makeText(context, text, duration).show();

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Context context = getApplicationContext();
                        String failure = "Failed! Error: " + e;
                        int duration = Toast.LENGTH_LONG;

                        Toast.makeText(context, failure, duration).show();




                    }
                });
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

     String getSaltString() {
        String SALTCHARS = "qwertyuiopasdfghjklzxcvbnmnABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
