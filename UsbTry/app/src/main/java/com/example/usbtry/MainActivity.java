package com.example.usbtry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usbtry.utils.Product;
import com.example.usbtry.utils.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {

    protected String TAG = "MyUsbActivity";
    private static final int TEXT = 1;
    private Button btn;
    private Button copy;
    private TextView ceshi;
    static List<Product> productList = new ArrayList<>();
    static List<Product> productList2 = new ArrayList<>();
    private final Comparefile compare = new Comparefile();
    static ArrayList<File> allFile = new ArrayList<>();
    static ArrayList<File> bfile = new ArrayList<>();
    static ArrayList<String> link = new ArrayList<>();
    static ArrayList<String> connect = new ArrayList<>();
    static ArrayList<String> sb = new ArrayList<>();
    private final Sum test = new Sum();
    private final Storage storage = new Storage();
    private final static String strFilePath = "/storage/usb1";
    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";
    public static String[] PERMISSION_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static Context mContext;


    @SuppressLint("ShowToast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filter.addAction(ACTION_USB_PERMISSION);
        registerReceiver(mUsbReceiver, filter);
        Set();
        checkpermission();
        MyThead myThead=new MyThead();
        new Thread(myThead).start();
        //显示当前系统插入USB设备
        UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        Toast.makeText(this.getApplicationContext(), "getDeviceList().size() = "+ mUsbManager.getDeviceList().size(), Toast.LENGTH_LONG).show();
        for (UsbDevice device : mUsbManager.getDeviceList().values()) {
            Log.d(TAG, "USB Device: " + device.toString());
        }
        btn.setOnClickListener(v -> {

            if (test.ljj().equals(test.niubi())) {
                ToastUtil.showToast("相同");

            } else {
                ToastUtil.showToast("不相同");
            }
        });
        copy.setOnClickListener(v -> {
            compare.readUDiskDevsList(strFilePath + "/test", "/vr/ASR");
        });
    }

//    public void testMyToast(String str) {
//        Toast.makeText(this.getApplicationContext(), str, Toast.LENGTH_LONG).show();
//    }
//
//    public static Context getMyContext() {
//        return mContext;
//    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
            String deviceName = usbDevice.getDeviceName();
            Log.e(TAG,"--- 接收到广播， action: " + action);
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                Log.e(TAG, "USB device is Attached: " + deviceName);
                testAssign();
                ceshi.setText("u盘已连接");
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                Log.e(TAG, "USB device is Detached: " + deviceName);
                ceshi.setText("u盘已断开");
            }
        }
    };
    public final static int REQUEST_EXTERNAL_STORAGE = 1;
    public final static int PERMISSION_REQUEST_CODE = 1;
    public void testAssign(){
        RecyclerView recyclerView1 = findViewById(R.id.names);
        MyAdapter adapter1 = new MyAdapter(productList);
        recyclerView1.setAdapter(adapter1);
        RecyclerView recyclerView2 = findViewById(R.id.md5s);
        MyAdapter2 adapter2 = new MyAdapter2(productList2);
        recyclerView2.setAdapter(adapter2);
    }
    public void checkpermission(){
        int permission = ActivityCompat.checkSelfPermission(this,
                PERMISSION_STORAGE[REQUEST_EXTERNAL_STORAGE]);
        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, PERMISSION_REQUEST_CODE);
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "PERMISSION" + permissions[i] + "GRANTED!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "PERMISSION" + permissions[i] + "DENIED!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void Set(){
        RecyclerView recyclerView1 = findViewById(R.id.names);
        RecyclerView recyclerView2 = findViewById(R.id.md5s);
        btn = findViewById(R.id.compare);
        copy = findViewById(R.id.clone);
        ceshi =findViewById(R.id.test);
        GifImageView mGifImv1 = (GifImageView)findViewById(R.id.gifImv1) ;
        mGifImv1.setImageResource(R.drawable.c);
        LinearLayoutManager layoutManagera = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManagera);
        layoutManagera.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManagerb = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManagerb);
        layoutManagerb.setOrientation(LinearLayoutManager.VERTICAL);
    }/**
     初始化
     **/

    public void unread() {
            compare.readUDiskDevsList(strFilePath + "/test", "/vr/ASR");//调用了u盘数据，只有插入u盘才能实现运行
            storage.readFile(new File(strFilePath, "test"));//遍历整个文件夹,将文件夹数据写入集合中
            storage.read2File(new File("vr", "ASR"));
            Collections.sort(allFile);//对集合进行排序
            Collections.sort(bfile);//对集合进行排序
            Log.i(TAG, String.valueOf(allFile));
            Log.i(TAG, String.valueOf(bfile));
            compare.Comparelist(allFile, bfile);//比较两个集合
            storage.write();
            Assignment();
            test.add();
            othersave(test.ljj());//md5总值
            load();

    }
    public static boolean isPathExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }
    public void Assignment() {
        if (bfile.size() > 0) {
            for (int i = 0; i < allFile.size(); i++) {//给数组赋值，并传总值
                String y = connect.get(i);
                Product a = new Product(y);
                productList2.add(a);
            }
        } else {
            Log.i(TAG, "sb");
        }
    }
    public void othersave(String otherText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("sb", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(otherText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//存储

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("sb");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    class MyThead implements Runnable{
        public void run(){
            if(isPathExist(strFilePath)){
                unread();
            }

        }
    }
    class MyTheadtwo implements Runnable{
        public void run(){
            if(isPathExist(strFilePath)){
                compare.readUDiskDevsList(strFilePath + "/test", "/vr/ASR");
                storage.readFile(new File(strFilePath, "test"));//遍历整个文件夹,将文件夹数据写入集合中
                storage.read2File(new File("vr", "ASR"));
                Collections.sort(allFile);//对集合进行排序
                Collections.sort(bfile);//对集合进行排序
            }

        }
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(mUsbReceiver);
    }
//    protected void onDestroy() {
//        super.onDestroy();
//       unregisterReceiver(mUsbReceiver);
//    }
}