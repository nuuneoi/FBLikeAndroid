package com.inthecheesefactory.lib.fblikeandroid;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;

/**
 * Created by nuuneoi on 4/25/2015.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.DEVELOPER_ERRORS);
    }
}
