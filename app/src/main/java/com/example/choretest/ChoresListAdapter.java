package com.example.choretest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.List;

public class ChoresListAdapter extends RecyclerView.Adapter<ChoresListAdapter.ViewHolder> {


    public List<Chores> choreList;

    public ChoresListAdapter(List<Chores> choreList){
        this.choreList = choreList;
    }

    public String choreId;

    public FirebaseFirestore dFirestore;



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

            descriptionText = (TextView) mView.findViewById(R.id.description_text);
            lengthText = (TextView) mView.findViewById(R.id.length_text);
            removeButton = (FloatingActionButton) mView.findViewById(R.id.removeChore);
        }
    }
}
