package com.example.choretest;

import androidx.annotation.NonNull;

public class userID {

    public String userId;

    public <T extends userID> T withId(@NonNull final String id){
        this.userId = id;
        return (T) this;
    }
}
