package com.example.choretest;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


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








        holder.removeButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v){

               // CharSequence rmText = "Chore Finished! Good job!";

                //int duration = Toast.LENGTH_LONG;
                DocumentReference doc =  dFirestore.collection("choreList").document(chore_id);

                doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();


                            //userid = "com.example.choretest.userID";

                            userid = context.getSharedPreferences("com.example.choretest", context.MODE_PRIVATE).getString("userID", "");

                            if(userid == "0"){

                                DocumentReference ref =  dFirestore.collection("userList").document("anya");

                                ref.update("points", FieldValue.increment(1));


                            }
                            else if (userid == "1"){
                                DocumentReference ref =  dFirestore.collection("userList").document("daddy");

                                ref.update("points", FieldValue.increment(1));

                            }

                            else if (userid == "2"){
                                DocumentReference ref =  dFirestore.collection("userList").document("benjamin");

                                ref.update("points", FieldValue.increment(1));

                            }

                            else if (userid == "3"){
                                DocumentReference ref =  dFirestore.collection("userList").document("gregory");

                                ref.update("points", FieldValue.increment(1));


                            }

                            else{

                            }
                        }
                    }
                });





                choreList.remove(position);

                //Toast.makeText(getApplicationContext(), rmText, duration).show();

                dFirestore.collection("choreList").document(chore_id)
                        .delete();


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
            lengthText = (TextView) mView.findViewById(R.id.points_text);
            removeButton = (FloatingActionButton) mView.findViewById(R.id.removeChore);
        }
    }
}
