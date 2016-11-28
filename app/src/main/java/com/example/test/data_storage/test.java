package com.example.test.data_storage;

import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;

import java.io.Serializable;
import java.util.Arrays;

public class test implements Serializable {
    int [] I1 ;
    String Timestamp;

    public test(int [] i1, String S)
    {
        I1 = i1;
        Timestamp = S;
    }

    @Override
    public String toString()
    {
        String item;

        item = Arrays.toString(I1) + " - " + "(" + Timestamp + ")";

        return item;
    }
}