package com.example.usbtry;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.usbtry.MainActivity.allFile;
import static com.example.usbtry.MainActivity.bfile;

public class Storage {
    private final Sum yueg = new Sum();

    public void readFile(File directory) {
        if(directory.listFiles()!=null)
        for (File file : directory.listFiles()) {
            if (file.isDirectory())
                readFile(file);
            else
                allFile.add(file);//将文件夹数据写入集合中
        }

    }//读值到集合

    public void read2File(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory())
                readFile(file);
            else
                bfile.add(file);////将文件夹数据写入集合中
        }
    }//读值到集合

    public void write() {

        FileOutputStream fileOutputStream = null;
        try {

            fileOutputStream = new FileOutputStream(new File("/vr/ASR/9.txt"));
            String x = yueg.niubi();
            Log.i("TAG", x);
            fileOutputStream.write(x.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
