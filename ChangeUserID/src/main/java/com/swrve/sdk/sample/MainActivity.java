package com.swrve.sdk.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.swrve.sdk.SwrveSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Button callback.  Changes user ID to 'User_A'
     * @param view
     */
    public void onChangeUserIDToA(View view) throws Exception {
        changeToUserID("User_A");
    }

    /**
     * Button callback.  Changes user ID to 'User_A'
     * @param view
     */
    public void onChangeUserIDToB(View view) throws Exception {
        changeToUserID("User_B");
    }

    /**
     * Button callback.  Changes user ID to 'User_A'
     * @param view
     */
    public void onChangeUserIDToNull(View view) throws Exception {
        changeToUserID(null);
    }

    /**
     * Utility function that changes the user id and sends diagnostic events
     * to make sure events are sent correctly with the old user ID and the
     * new user ID.
     * @param userID
     */
    protected void changeToUserID(String userID) throws Exception {
        String oldUserID = SwrveSDK.getUserId();

        SwrveSDK.event("Changing from " + oldUserID  + " to " + userID);
        SwrveSDK.sendQueuedEvents();

        ((SampleApplication)(getApplication())).getSwrveIdentityUtility().changeUserID(this, userID);
        saveUserToPreferences(userID);

        SwrveSDK.event("Changed to " + userID + " from " + oldUserID);
        SwrveSDK.sendQueuedEvents();
    }

    protected void saveUserToPreferences(String userID) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplication());
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("userID", userID);
        prefEditor.commit();
    }
}
