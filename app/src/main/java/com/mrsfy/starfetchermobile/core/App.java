package com.mrsfy.starfetchermobile.core;

import android.app.Application;
import android.content.Context;

/**
 * Created by mrsfy on 11.02.2016.
 */
public class App extends Application {
    private static App sInstance;
    public static App getInstance(){
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
