package com.swrve.usersampleid;

import android.app.Application;
import android.util.Log;
import com.swrve.sdk.SwrveSDK;
import com.swrve.sdk.config.SwrveConfig;

public class SampleApplication extends Application {

    private static final String LOG_TAG = "SwrveSample";

    //  FIXME Add your own App ID and Api Key here
    private static final int YOUR_APP_ID = 0;
    private static final String YOUR_API_KEY = "<add_your_api_key_here>";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            SwrveConfig config = new SwrveConfig();
            SwrveSDK.createInstance(this, YOUR_APP_ID, YOUR_API_KEY, config);
        } catch (Exception exp) {
            Log.e(LOG_TAG, "Could not initialize the Swrve SDK", exp);
        }
    }
}
