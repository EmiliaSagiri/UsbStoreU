package com.example.usbtry.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.usbtry.InitApplication;


/**
 * toast显示类，可以在子线程直接调用
 * 封装的Toast类
 */
public class ToastUtil {
    private static Toast toast;

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    public static void showToast(final String text, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            show(text, duration);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(text, duration);
                }
            });
        }
    }

    private static void show(String text, int duration) {
        if (toast != null) {
            toast.cancel();
        }
        Log.d("ljj","show:  "+InitApplication.getApplication());
        toast = Toast.makeText(InitApplication.getApplication().getApplicationContext(), text, duration);
        toast.show();
    }

}
