package com.lbjfan.testdemo;

import android.app.Application;
import android.content.Context;

import com.lbjfan.testdemo.manager.AppManager;

/**
 * Created by admin on 2017/2/27.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppManager.getInstance().appInit(this);
    }
}
