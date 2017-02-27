package com.lbjfan.testdemo.manager;

import android.app.Application;
import android.content.Context;

/**
 * Created by ${lbjfan} on 16-10-19.
 */
public class AppManager {

    private static AppManager appManager;
    private Context appContext;

    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    public void appInit(Context context){
        appContext = context;
    }

    public Context getAppContext(){
        return appContext;
    }
}
