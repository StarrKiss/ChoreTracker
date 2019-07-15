package com.example.choretest;

import java.util.Random;

public class chore {
    public String description;
    public int length;
    public String appID;

    public chore(){

    }

    public chore(String appID,String description, int length){
        this.description = description;

        this.length = length;

        this.appID = appID;
    }


}
