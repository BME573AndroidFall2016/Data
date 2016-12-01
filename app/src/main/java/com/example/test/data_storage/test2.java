package com.example.test.data_storage;


import java.io.Serializable;
import java.util.Arrays;

public class test2 implements Serializable{
    int Datatype;               // Labels the data as a type of sensor values
    double D1;                  // A single double value of the 10 set from the sensor
    String Timestamp;           // The generated timestamp when storing the file

    public test2(int datatype, double i1, String S)     // Constructor for taking in the arguments from external source
    {
        Datatype = datatype;
        D1 = i1;
        Timestamp = S;
    }

    @Override
    public String toString()            // Casts the data into a readable string for displaying in listview
    {
        String item;

        item = D1 + " - " +  Timestamp;

        return item;
    }
}
