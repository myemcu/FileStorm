package com.myemcu.app_15filestorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWriteTxt;
    private EditText mReadTxt;
    private Button mWrite;
    private Button mRead;

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
        
    }

    private String read() {
        return null;
    }

    private void findViews() {
        mWriteTxt = (EditText) findViewById(R.id.writeTxt);
        mReadTxt = (EditText) findViewById(R.id.readTxt);
        mWrite = (Button)   findViewById(R.id.write);
        mRead = (Button)   findViewById(R.id.read);
    }
}
