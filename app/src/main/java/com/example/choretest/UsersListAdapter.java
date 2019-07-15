package com.example.choretest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;



    public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {


        public List<Users> userList;

        public UsersListAdapter(List<Users> userList){
            this.userList = userList;
        }

        public String user_id;

        public FirebaseFirestore dFirestore;



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
            return  new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull com.example.choretest.UsersListAdapter.ViewHolder holder, final int position) {
            holder.nameText.setText(userList.get(position).getName());
            holder.pointText.setText(userList.get(position).getPoints());

            final String user_id = userList.get(position).userId;









        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            View mView;

            public TextView nameText;
            public TextView pointText;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mView = itemView;

                nameText = (TextView) mView.findViewById(R.id.name_text);
                pointText = (TextView) mView.findViewById(R.id.points_text);
            }
        }
    }
