package com.example.usbtry;

import android.util.Log;
import android.widget.Toast;

import com.example.usbtry.utils.MD5ovo;
import com.example.usbtry.utils.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.usbtry.MainActivity.connect;
import static com.example.usbtry.MainActivity.link;
import static java.security.AccessController.getContext;

public class Comparefile {
    private static final String TAG = "666";
    private final MD5ovo sd = new MD5ovo();

    public void Comparelist(ArrayList<File> a, ArrayList<File> b) {
        for (int i = 0; i < a.size(); i++) {
            String md = sd.md5sum(String.valueOf(a.get(i)));
            String sgou = sd.md5sum(String.valueOf(b.get(i)));
            link.add(md);
            connect.add(sgou);
            if (md.equals(sgou)) {
                Log.i(TAG, "相同");
            } else {
                Log.i(TAG, "Comparelist:失败");
                ToastUtil.showToast(b.get(i) + "路径未传输");
            }
        }
    }//比较
    public void readUDiskDevsList(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            if(a!=null) {
                String[] file = a.list();
                File temp;
                //读取文件夹中的文件
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }

                    if (temp.isFile()) {   //全部复制
                        //建立通道对象
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + "/" +
                                (temp.getName()).toString());
//                    Log.d("sb", "readUDiskDevsList: " + temp.getName() + "复制成功");
                        //定义存储空间
                        byte[] b = new byte[1024 * 5];
                        //开始读文件
                        int len;
                        while ((len = input.read(b)) != -1) {
                            //将b中的数据写入到FileOutputStream对象中
                            output.write(b, 0, len);
                        }
                        //关闭流
                        output.flush();
                        output.close();
                        input.close();
                    } else if (temp.isDirectory()) {//如果是子文件夹
                        readUDiskDevsList(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }

                }
            }

            } catch(Exception e){
                System.out.println("复制整个文件夹内容操作出错");
                e.printStackTrace();
            }
        }
        }//遍历


