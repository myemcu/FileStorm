package com.myemcu.app_15filestorm;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

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
               //判断手机中的SD卡是否存在
               if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                   File sdCardDir = Environment.getExternalStorageDirectory(); // 获取SD卡路径
                   File destFile = new File(sdCardDir.getCanonicalPath()+File.separator+fileName); // 根据路径和文件名创建文件

                   RandomAccessFile raf = new RandomAccessFile(destFile,"rw");
                   raf.seek(destFile.length()); // 把指针定位到文件末尾
                   raf.write(s.getBytes());     // 在文件末尾追加新内容
                   raf.close();                 // 关闭文件
               }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String read() {
        StringBuilder sBuilder = new StringBuilder("");
        try {   //判断手机中的SD卡是否存在
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    File sdCardDir = Environment.getExternalStorageDirectory(); // 获取SD卡路径
                    File destFile = new File(sdCardDir.getCanonicalPath()+File.separator+fileName); // 根据路径和文件名创建文件

                    FileInputStream fis = new FileInputStream(destFile);        // 创建文件输入流
                    byte[] buffer = new byte[64];                               // 定义缓冲区大小
                    int hasread;
                    while ((hasread=fis.read(buffer)) != -1) { // 一直循环到文件末尾
                        sBuilder.append(new String(buffer,0,hasread)); // 在字符串后追加内容
                    }
                    return sBuilder.toString();
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void findViews() {
        mWriteTxt = (EditText) findViewById(R.id.writeTxt);
        mReadTxt = (EditText) findViewById(R.id.readTxt);
        mWrite = (Button)   findViewById(R.id.write);
        mRead = (Button)   findViewById(R.id.read);
    }
}
