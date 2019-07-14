package com.example.choretest;

import androidx.annotation.NonNull;

public class choreId {

    public String choreId;

    public <T extends choreId> T withId(@NonNull final String id){
        this.choreId = id;
        return (T) this;
    }
}
