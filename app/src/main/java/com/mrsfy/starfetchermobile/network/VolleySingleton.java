package com.mrsfy.starfetchermobile.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mrsfy.starfetchermobile.core.App;

/**
 * Created by mrsfy on 11.02.2016.
 */
public class VolleySingleton {
    private static RequestQueue mRequestQueue;
    private static VolleySingleton mInstance;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(App.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if ( mRequestQueue == null ) {
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
