package com.example.choretest;

public class Chores extends choreId {

    String description;

    int length;

    public Chores(){

    }



    public Chores(String description, int length) {
        this.description = description;
        this.length = length;
    }

    public String getChore() {
        return description;
    }

    public void setChore(String description) {
        this.description = description;
    }

    public String getTime() {
        return "" + length + "m";
    }

    public void setTime(int length) {
        this.length = length;
    }

}
