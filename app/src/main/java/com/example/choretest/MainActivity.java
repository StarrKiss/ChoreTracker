package com.example.choretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mMainList;
    private static final String TAG = "MainActivity";

    public FirebaseFirestore mFirestore;

    private ChoresListAdapter ChoresListAdapter;

    private List<Chores> choreList;

    public String selection;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainList = (RecyclerView) findViewById(R.id.main_list);
        mFirestore = FirebaseFirestore.getInstance();
        choreList = new ArrayList<>();
        ChoresListAdapter = new ChoresListAdapter(choreList);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter((ChoresListAdapter));

        SharedPreferences prefs = this.getSharedPreferences("com.example.choretest", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();



        if(prefs.getString("userID", "") == "") {
            Intent intent = new Intent(this, userSelection.class);

            startActivity(intent);


            finishSelection();






        }







        mFirestore.collection("choreList").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "ERROR: " + e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        Chores chores = doc.getDocument().toObject(Chores.class).withId(doc.getDocument().getId());
                        choreList.add(chores);


                        ChoresListAdapter.notifyDataSetChanged();





                    }

                    else if (doc.getType() == DocumentChange.Type.REMOVED){
                        Chores chores = doc.getDocument().toObject(Chores.class).withId(doc.getDocument().getId());

                        choreList.remove(chores);
                        ChoresListAdapter.notifyDataSetChanged();

                    }
                }


            }
        });
    }



    public void addDocument(View view){
        Intent intent = new Intent(this, additem.class);

        startActivity(intent);

    }

    public void viewUsers(View view){
        Intent intent = new Intent(this, UserList.class);

        startActivity(intent);
    }

    void finishSelection(){

        SharedPreferences prefs = this.getSharedPreferences("com.example.choretest", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        Intent mIntent = getIntent();

        selection = mIntent.getStringExtra("selection");

        editor.putString("userID", selection);

        editor.commit();


    }






}


