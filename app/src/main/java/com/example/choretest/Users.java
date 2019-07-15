package com.example.choretest;

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

    public void setPoints(int points) {
        this.points = points;
    }

}


