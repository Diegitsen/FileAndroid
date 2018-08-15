package com.example.diego.fileexample;

import android.os.Environment;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText etMessage;
    TextView tvTheMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = findViewById(R.id.etMessage);
        tvTheMessage = findViewById(R.id.tvTheMessage);
    }


    public void WriteInTheMemory(View view)
    {
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"AppFile");

            if(!dir.exists())
            {
                dir.mkdir();
            }

            File file = new File(dir, "MyMessage.txt");
            String message = etMessage.getText().toString();

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
                etMessage.setText("");
                Toast.makeText(getApplicationContext(), "Message saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else
        {
            Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void ReadInTheMemory(View view)
    {
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath()+"AppFile");

        File file = new File(dir, "MyMessage.txt");
        String message;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while((message = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(message + "\n");
            }

            tvTheMessage.setText(stringBuffer.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


