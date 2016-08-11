package com.myemcu.app_15filestorm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    private EditText mWriteTxt;
    private EditText mReadTxt;
    private Button mWrite;
    private Button mRead;
    private String fileName = "MyFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(mWriteTxt.getText().toString());  // 写入输入内容
            }
        });

        mRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReadTxt.setText(read());   // 显示读出内容
            }
        });
    }

    private void write(String s) {
        try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
                PrintStream ps = new PrintStream(fos);
                ps.print(s);
                ps.close();
                fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String read() {
        StringBuilder sBuilder = new StringBuilder("");
        try {
                FileInputStream is = openFileInput(fileName);
                byte[] buffer = new byte[64];
                int hasRead;
                while ((hasRead=is.read(buffer)) != -1) {
                    sBuilder.append(new String(buffer,0,hasRead));
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    private void findViews() {
        mWriteTxt = (EditText) findViewById(R.id.writeTxt);
        mReadTxt = (EditText) findViewById(R.id.readTxt);
        mWrite = (Button)   findViewById(R.id.write);
        mRead = (Button)   findViewById(R.id.read);
    }
}
