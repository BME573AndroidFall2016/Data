package com.example.test.data_storage;

import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;

import java.io.Serializable;
import java.util.Arrays;

public class test implements Serializable {
    int Datatype;
    double[] D1;
    String[] Timestamp;

    public test(int datatype, double[] i1, String[] S)
    {
        Datatype = datatype;
        D1 = i1;
        Timestamp = S;
    }

    @Override
    public String toString()
    {
        String item;

        item = Arrays.toString(D1) + " - " +  Arrays.toString(Timestamp);

        return item;
    }

    public void putData(int datatype, double[] sensorvalues, String[] times)
    {
        Datatype = datatype;
        D1 = sensorvalues;
        Timestamp = times;
    }
}