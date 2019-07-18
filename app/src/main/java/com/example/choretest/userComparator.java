package com.example.choretest;

import java.util.Comparator;
import java.lang.Integer;

public class userComparator implements Comparator<Users> {


    @Override
    public int compare(Users o1, Users o2) {

        return  Integer.compare(o2.getComparePoints(), o1.getComparePoints());
    }

}
