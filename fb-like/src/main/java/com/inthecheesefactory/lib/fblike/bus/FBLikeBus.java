package com.inthecheesefactory.lib.fblike.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by nuuneoi on 4/25/2015.
 */
public class FBLikeBus extends Bus {

    Handler handler = new Handler(Looper.getMainLooper());

    private static FBLikeBus instance;

    public static FBLikeBus getInstance() {
        if (instance == null)
            instance = new FBLikeBus();
        return instance;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    FBLikeBus.super.post(event);
                }
            });
        }
    }
}
