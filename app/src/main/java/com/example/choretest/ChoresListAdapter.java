package com.example.choretest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class ChoresListAdapter extends RecyclerView.Adapter<ChoresListAdapter.ViewHolder> {


    public List<Chores> choreList;

    public ChoresListAdapter(List<Chores> choreList){
        this.choreList = choreList;
    }

    public String choreId;

    public FirebaseFirestore dFirestore;


    Context context;

    public String userid;





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return  new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.lengthText.setText(choreList.get(position).getTime());
        holder.descriptionText.setText(choreList.get(position).getChore());

        final String chore_id = choreList.get(position).choreId;

        dFirestore = FirebaseFirestore.getInstance();



        SharedPreferences prefs = context.getSharedPreferences("userShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();




        holder.removeButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){

               // CharSequence rmText = "Chore Finished! Good job!";

                //int duration = Toast.LENGTH_LONG;


                DocumentReference doc;
                doc = dFirestore.collection("choreList").document(chore_id);


                doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();



                           // Long longObject = document.getLong("length");
                            //furesSystem.out.println("longObject: " + longObject);
                            //userid = "com.example.choretest.userID";

                            SharedPreferences prefs = context.getSharedPreferences("userShared", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            userid = prefs.getString("userID", "");

                            if(userid.equals("0")){

                                DocumentReference ref =  dFirestore.collection("userList").document("anya");

                                ref.update("points", FieldValue.increment(document.getLong("length").intValue()));


                            }
                             else if (userid.equals("1")){
                                DocumentReference ref =  dFirestore.collection("userList").document("daddy");

                                ref.update("points", FieldValue.increment(document.getLong("length").intValue()));

                            }

                             else if (userid.equals("2")){
                                DocumentReference ref =  dFirestore.collection("userList").document("benjamin");

                                ref.update("points", FieldValue.increment(document.getLong("length").intValue()));







                            }

                            else if (userid.equals("3")){
                                DocumentReference ref =  dFirestore.collection("userList").document("gregory");

                                ref.update("points", FieldValue.increment(document.getLong("length").intValue()));


                            }

                            else{


                            }



                            choreList.remove(position);

                            //Toast.makeText(getApplicationContext(), rmText, duration).show();

                            dFirestore.collection("choreList").document(chore_id)
                                    .delete();

                        }
                    }
                });







            }
        });

    }

    @Override
    public int getItemCount() {
        return choreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView descriptionText;
        public TextView lengthText;
        public FloatingActionButton removeButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            descriptionText = (TextView) mView.findViewById(R.id.name_text);
            context = descriptionText.getContext();
            lengthText = (TextView) mView.findViewById(R.id.points_text);
            removeButton = (FloatingActionButton) mView.findViewById(R.id.removeChore);
        }
    }
}
