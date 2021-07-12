package com.dbreen.appstorereview;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.swrve.sdk.SwrveSDK;
import com.swrve.sdk.config.SwrveConfig;
import com.swrve.sdk.config.SwrveEmbeddedMessageConfig;
import com.swrve.sdk.messaging.SwrveEmbeddedMessageListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MyApplication extends Application {
    private Activity mCurrentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            SwrveConfig config = new SwrveConfig();
            SwrveEmbeddedMessageListener embeddedMessageListener = (context, message) -> {
                // If you want to track an impression event
                SwrveSDK.embeddedMessageWasShownToUser(message);

                Log.d("embedded data",message.getData());

                try {
                    JSONObject jObj = new JSONObject(message.getData());
                    if (jObj.has("campaign_type") && jObj.getString("campaign_type").equals("app_review")) {
                        Log.d("campaign_type", "is app_review");

                        showRateApp();
                    }
                }
                catch (JSONException ex){
                    Log.e("JsonParser Example","unexpected JSON exception", ex);
                }
            };
            SwrveEmbeddedMessageConfig embeddedMessageConfig = new SwrveEmbeddedMessageConfig.Builder()
                    .embeddedMessageListener(embeddedMessageListener)
                    .build();

            config.setEmbeddedMessageConfig(embeddedMessageConfig);
            SwrveSDK.createInstance(this, 30523, "PuUbaNHH7q0pjhzFvBs", config);
        } catch (IllegalArgumentException exp) {
            Log.e("SwrveDemo", "Could not initialize the Swrve SDK", exp);
        }
    }

    // Shows the app rate dialog box using In-App review API
    // The app rate dialog box might or might not shown depending
    // on the Quotas and limitations
    public void showRateApp() {
        ReviewManager reviewManager = ReviewManagerFactory.create(this);
        Task <ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Getting the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task <Void> flow = reviewManager.launchReviewFlow(getCurrentActivity(), reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown.
                });
            }
        });
    }

    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }
}