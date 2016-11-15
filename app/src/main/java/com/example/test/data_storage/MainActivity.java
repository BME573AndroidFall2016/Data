package com.example.test.data_storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    ListView L1;
    Button B1,B2;
    Data D1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L1 = (ListView) findViewById(R.id.listview1);
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
                    (this,android.R.layout.simple_list_item_1,D1.ints);
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
    public void Reset(View v) {try {
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

    public void WriteOBJ(View v){
        try {
            FileOutputStream fout = openFileOutput("test.txt",MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(D1.ints);
            oos.close();
            Toast.makeText(getBaseContext(),"File Saved!",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
    public void ReadObj(View v){
        ArrayList<Integer> T1;
        try {
            FileInputStream fin = openFileInput("test.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            T1 = new ArrayList<Integer>(Arrays.asList((Integer[])ois.readObject()));
            ois.close();
            fin.close();
            ArrayAdapter<Integer> AR = new ArrayAdapter<>
                    (this,android.R.layout.simple_list_item_1,T1);
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
    public void ResetOBJ(View v){
        try {
            FileOutputStream fout = openFileOutput("test.txt",MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(D1.intr);
            oos.close();
            Toast.makeText(getBaseContext(),"File cleared successfully!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void Delete(View v) {
        try
        {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            File f = new File(System.getProperty("user.dir"),"test.txt");
            fos.close();
            f.delete();
            Toast.makeText(getBaseContext(),"File deleted successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {e.printStackTrace();}
    }
}
