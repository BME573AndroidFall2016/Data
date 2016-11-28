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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView LV1;
    ArrayList<test> test1 = new ArrayList<test>();
    Button B1;
    boolean buttonpress = false;
    int clickcount = 0;
    int[] G1 = {clickcount};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LV1 = (ListView) findViewById(R.id.listview2);
        Button B1 = (Button)findViewById(R.id.test);
    }



    public void testwrite(View v) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        if (buttonpress = false)
        {
            test t1 = new test(G1,currentDateTimeString);
            test1.add(t1);
            buttonpress = true;
        }
        else {};
        try {
            FileOutputStream fout1 = openFileOutput("test-test.txt", MODE_PRIVATE);
            ObjectOutputStream oos1 = new ObjectOutputStream(fout1);
            oos1.writeObject(test1);
            oos1.close();
            Toast.makeText(getBaseContext(), "File Saved!", Toast.LENGTH_SHORT).show();
            B1.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testread(View v) {
        ArrayList<test> Y1;
        try {
            FileInputStream fin1 = openFileInput("test-test.txt");
            ObjectInputStream ois1 = new ObjectInputStream(fin1);
            Y1 = (ArrayList<test>) (ois1.readObject());
            ois1.close();
            fin1.close();
            ArrayAdapter testAdapter = new ArrayAdapter<test>(this, android.R.layout.simple_list_item_1, Y1);
            LV1.setAdapter(testAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testadd(View v){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        clickcount = clickcount + 1;
        int[] G2 = {clickcount};
        test t2 = new test(G2,currentDateTimeString);
        test1.add(t2);
    }
}
