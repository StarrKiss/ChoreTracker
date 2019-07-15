package com.example.choretest;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class preferenceManager {


     Context context;

    public  SharedPreferences publicPref(){





        SharedPreferences prefs = context.getSharedPreferences("userPrefs", 0);

        return prefs;
    }


}
