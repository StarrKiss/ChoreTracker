package com.example.choretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class UserList extends AppCompatActivity {

    private RecyclerView mMainList;

    public FirebaseFirestore mFirestore;

    private UsersListAdapter UsersListAdapter;

    private List<Users> usersList;

    private static final String TAG = "UserList";

    public TextView winnerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mMainList = (RecyclerView) findViewById(R.id.users);
        mFirestore = FirebaseFirestore.getInstance();
        usersList = new ArrayList<>();
        UsersListAdapter = new UsersListAdapter(usersList);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter((UsersListAdapter));
        CollectionReference collectionRef = mFirestore.collection("userList");

        winnerText = (TextView) findViewById(R.id.winnerName);

        Query query = collectionRef.orderBy("points",Query.Direction.DESCENDING).limit(1);


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.getData() ==null){
                            winnerText.setText("Nobody won!");
                        }

                        winnerText.setText(document.getString("name"));
                    }
                }
            }
        });

        mFirestore.collection("userList").addSnapshotListener(new EventListener<QuerySnapshot>() {



            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "ERROR: " + e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        Users users = doc.getDocument().toObject(Users.class).withId(doc.getDocument().getId());
                        usersList.add(users);

                        UsersListAdapter.notifyDataSetChanged();


                    }



                }


            }
        });



    }
    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

}
