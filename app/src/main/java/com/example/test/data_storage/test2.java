package com.example.test.data_storage;


import java.io.Serializable;
import java.util.Arrays;

public class test2 implements Serializable{
    int Datatype;
    double D1;
    String Timestamp;

    public test2(int datatype, double i1, String S)
    {
        Datatype = datatype;
        D1 = i1;
        Timestamp = S;
    }

    @Override
    public String toString()
    {
        String item;

        item = D1 + " - " +  Timestamp;

        return item;
    }

    public void putData(int datatype, double sensorvalues, String times)
    {
        Datatype = datatype;
        D1 = sensorvalues;
        Timestamp = times;
    }
}
