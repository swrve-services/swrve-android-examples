package com.dbreen.splashscreenexample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.swrve.sdk.SwrveLogger;
import com.swrve.sdk.SwrveNotificationConfig;
import com.swrve.sdk.SwrveSDK;
import com.swrve.sdk.config.SwrveConfig;

public class MyApplication extends Application {
    // TODO - Replace the app_id and api_key to point to your own dashboard
    // TODO - If using push don't forget to add your google-services.json file to the project
    
    int app_id = -1;
    String api_key = "<api_key_here>";
    
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            SwrveLogger.setLogLevel(2);
            SwrveConfig config = new SwrveConfig();

            // To use the EU stack, include this in your config.
            // config.setSelectedStack(SwrveStack.EU);

            // Swrve Push Config
            NotificationChannel channel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel("123", "Devapp swrve default channel", NotificationManager.IMPORTANCE_DEFAULT);
                if (getSystemService(Context.NOTIFICATION_SERVICE) != null) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(channel);
                }
            }
            SwrveNotificationConfig.Builder notificationConfig = new SwrveNotificationConfig.Builder(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, channel)
                    .activityClass(MainActivity.class)
                    .largeIconDrawableId(R.mipmap.ic_launcher)
                    .accentColorHex("#3949AB");
            config.setNotificationConfig(notificationConfig.build());

            // Swrve Initialization
            SwrveSDK.createInstance(this, app_id, api_key, config);
        } catch (IllegalArgumentException exp) {
            Log.e("SwrveDemo", "Could not initialize the Swrve SDK", exp);
        }
    }
}
