package com.example.choretest;

import com.google.firebase.firestore.auth.User;

public class Users extends userID{

    String name;

    int points;

    public Users(){

    }



    public Users(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return "" + points + "m";
    }

    public int getComparePoints(){
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }




}


