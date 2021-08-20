package com.example.usbtry;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by yuanpk on 2018/7/10  15:09
 * <p>
 * Description:TODO
 */

public class InitApplication extends Application {

    private static Application mApplication;
    private static Context sApplicationContext;
    private final boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ljj","onCreate:  ");
        mApplication = this;
        sApplicationContext = getApplicationContext();

    }


    public static Application getApplication() {
        return mApplication;
    }

}
