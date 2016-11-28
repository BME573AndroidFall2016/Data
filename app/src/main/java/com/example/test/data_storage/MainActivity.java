package com.example.test.data_storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    ListView L1;
    Data D1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L1 = (ListView) findViewById(R.id.listview2);
        D1 = new Data();

    }

    public void Write(View v) {
        try {
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(D1.ints.toString());
            outputWriter.write("\n");
            outputWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Read(View v) {
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            ArrayAdapter<Integer> AR = new ArrayAdapter<Integer>
                    (this, android.R.layout.simple_list_item_1, D1.ints);
            L1.setAdapter(AR);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Reset(View v) {
        try {
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write("");
            outputWriter.close();
            Toast.makeText(getBaseContext(), "File cleared successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WriteOBJ(View v) {
        try {
            FileOutputStream fout = openFileOutput("test1.txt", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(D1.list1);
            oos.close();
            Toast.makeText(getBaseContext(), "File Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void ReadObj(View v) {
        ArrayList<Integer> T1;
        try {
            FileInputStream fin = openFileInput("test1.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            T1 = (ArrayList<Integer>) (ois.readObject());
            ois.close();
            fin.close();
            ArrayAdapter<Integer> AR = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, T1);
            L1.setAdapter(AR);
        } catch (FileNotFoundException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (IOException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void ResetOBJ(View v) {
        try {
            FileOutputStream fout = openFileOutput("test.txt", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            D1.list1.clear();
            oos.writeObject(D1.list1);
            oos.close();
            Toast.makeText(getBaseContext(), "File cleared successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void InitializeOBJ(View v) {
        try {
            D1.list1.clear();
            D1.list1.add(1);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void ADDOBJ(View v) {
        try {
            int n = D1.list1.get(D1.list1.size() - 1);
            D1.list1.add(n + 1);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

    }

    public void Delete(View v) {
        try {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            File f = new File(System.getProperty("user.dir"), "test.txt");
            fos.close();
            f.delete();
            Toast.makeText(getBaseContext(), "File deleted successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Next(View v) {
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        MainActivity.this.startActivity(myIntent);
    }
}