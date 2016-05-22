package bish.flickrdemo.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by BB045296 on 5/21/2016.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private static AppController myInstance;
    private RequestQueue myRequestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
        myInstance = this;
    }

    public static synchronized AppController getInstance() {
        return myInstance;
    }

    public RequestQueue getRequestQueue() {
        if (myRequestQueue == null) {
            myRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return myRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (myRequestQueue != null) {
            myRequestQueue.cancelAll(tag);
        }
    }

}
