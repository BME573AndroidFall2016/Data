package com.example.test.data_storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView LV1;
    //ArrayList<test2> Q1 = new ArrayList<test2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LV1 = (ListView) findViewById(R.id.listview2);
    }

    public void testwrite(View v){
        ArrayList<test2> write = new ArrayList<test2>();   // Create an arraylist object that can be read by listview and stored as object into file
        int type = 1;                               // Type of data (ECG - 1, etc) *essentially placeholder
        double[] dset = {1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,1.11};     // Set of 10 doubles from sensor
        long milli = System.currentTimeMillis();    // Get time of system when button pressed
        long buffer = 100;                          // Create a time "buffer" that allows for the timestamps to differ by a certain amount of time
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SS");    // Create a format that the timestamp is im
        Date result = new Date(milli);                                          // Casts the system time in millisecs to a Date object
        test2[] tt2 = new test2[dset.length];                                   // Initialize object array of data class with lenth of incoming data array
        for (int i = 0; i < dset.length; i++)
        {
            tt2[i] = new test2(type,dset[i],sdf.format(result));    // Set data into the object array tagged with timestamp
            milli += buffer;                                        // Add buffer to the initial time
            result = new Date(milli);                               // Update the timestamp with the buffered value
        }
        for (int i = 0; i < dset.length; i++)
        {
            write.add(tt2[i]);     // Add the array of data objects to the arraylist object
        }
        try {
            FileOutputStream fout = openFileOutput("data.txt", MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(write);
            oos.close();
            Toast.makeText(getBaseContext(), "File Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testread(View v) {
        ArrayList<test2> read;
        try {
            FileInputStream fin = openFileInput("data.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            read = (ArrayList<test2>) (ois.readObject());
            ois.close();
            fin.close();
            ArrayAdapter testAdapter = new ArrayAdapter<test2>(this, android.R.layout.simple_list_item_1, read);    // Create an adapter to display the data to listview
            LV1.setAdapter(testAdapter);        // Set adapter to listview
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
