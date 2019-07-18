package com.example.choretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

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

    public TextView name;

    public TextView ifChores;





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

        SharedPreferences prefs = this.getSharedPreferences("userShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        name = (TextView) findViewById(R.id.name);


        String userid = prefs.getString("userID", "");

        int count = count = mMainList.getAdapter().getItemCount();

        ifChores = (TextView) findViewById(R.id.chorehere);


        if(prefs.getString("userID", "") == "") {
            Intent intent = new Intent(this, userSelection.class);

            startActivity(intent);

        }


        if(userid.equals("0")){

            name.setText("Agnes!");

        }
        else if (userid.equals("1")){
            name.setText("John!");

        }

        else if (userid.equals("2")){


            name.setText("Benjamin!");





        }

        else if (userid.equals("3")){
            name.setText("Gregory!");

        }

        if( count > 1){
            ifChores.setText("You have " + count + " chores!");

        }

        if (count == 1){
            ifChores.setText("You have " + count + " chore!");
        }
        if(count == 0){
            ifChores.setText("You have no chores! Good Job!");

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

                        int count = count = mMainList.getAdapter().getItemCount();


                        ifChores.setText("You have " + count + " chores!");






                    }

                    else if (doc.getType() == DocumentChange.Type.REMOVED){
                        Chores chores = doc.getDocument().toObject(Chores.class).withId(doc.getDocument().getId());

                        choreList.remove(chores);
                        ChoresListAdapter.notifyDataSetChanged();

                        int count = count = mMainList.getAdapter().getItemCount();

                        if( count > 1){
                            ifChores.setText("You have" + count + "chores!");

                        }

                         if (count == 1){
                            ifChores.setText("You have " + count + " chore!");
                        }
                        if(count == 0){
                            ifChores.setText("You have no chores! Good Job!");

                        }


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

    public void changeSettings(View view){
        Intent intent = new Intent(this, settings.class);

        startActivity(intent);
    }







}


