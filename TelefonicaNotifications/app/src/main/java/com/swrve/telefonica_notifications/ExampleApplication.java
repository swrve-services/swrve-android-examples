package com.swrve.telefonica_notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.swrve.sdk.SwrveSDK;
import com.swrve.sdk.SwrveSilentPushListener;
import com.swrve.sdk.config.SwrveConfig;
import com.swrve.sdk.config.SwrveStack;

import com.telefonica.snlib.SmartNotifLib;

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SwrveConfig config = new SwrveConfig();
        config.setSelectedStack(SwrveStack.EU);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "default",
                    "description",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            config.setDefaultNotificationChannel(channel);
        }

        SwrveSDK.createInstance(this, <replace me>, <replace me>, config);

        SwrveSDK.setSilentPushListener(new SwrveSilentPushListener() {
            @Override
            public void onSilentPush(Context context, JSONObject payload) {
                try {
                    String campaignId = payload.getString("snCampaignId");
                    String contentId = payload.getString("snContentId");
                    long timeout = Long.parseLong(payload.getString("snTimeout"));

                    SmartNotifLib.queueNotification(
                            getApplicationContext(),
                            payload.toString(),
                            campaignId,
                            contentId,
                            timeout,
                            "com.telefonica.snlibnotifications.PREPARE_NOTIFICATION"
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        SmartNotifLib.initialize(this, <replace me>);
        SmartNotifLib.setUserId(getApplicationContext(), SwrveSDK.getUserId());
        SmartNotifLib.setLogEnabled(getApplicationContext(), false, false);
    }
}
