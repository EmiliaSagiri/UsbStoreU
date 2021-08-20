package com.example.usbtry;

import com.example.usbtry.utils.Product;

import java.io.File;

import static com.example.usbtry.MainActivity.bfile;
import static com.example.usbtry.MainActivity.connect;
import static com.example.usbtry.MainActivity.link;
import static com.example.usbtry.MainActivity.productList;
import static com.example.usbtry.MainActivity.sb;

public class Sum {
    public String ljj(){
        String sum = "";
        for(int i = 0; i<12; i++){//给数组赋值，并传总值
            String x=link.get(i)+"//";
            sum=sum+x;
        }
        return sum;
    }
    public String niubi(){
        String Log = "";
        for(int i = 0; i<12; i++){//给数组赋值，并传总值
            String x=connect.get(i)+"//";
            Log=Log+x;
        }
        return Log;
    }
    public void add(){
        for (File string : bfile) {
            if (string.getName().endsWith(".jpg")||string.getName().endsWith(".jpeg"))
            {
                sb.add(string.getPath());
            }
        }
        for(int i=0;i<12;i++){
            Product b =new Product(sb.get(i));
            productList.add(b);
        }
    }
}
