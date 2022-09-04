package com.jamesfchen.bundle1;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.jamesfchen.lifecycle.AppLifecycle;

/**
 * Copyright ® $ 2017
 * All right reserved.
 *
 * @author: jamesfchen
 * @since: Mar/16/2019  Sat
 */
@AppLifecycle
public class AppLifecycleObserver implements LifecycleObserver {
    //can receive zero or one argument
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void connectListener(LifecycleOwner lifecycleOwner) {
        Log.d("cjf", "connectListener");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground(LifecycleOwner lifecycleOwner) {
        Log.d("cjf", "onBackground");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void disconnectListener(LifecycleOwner lifecycleOwner) {
        Log.d("cjf", "disconnectListener");
    }

    //Methods annotated with ON_ANY can receive the second argument
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Log.d("cjf", "onAny:"+event);


    }
}
