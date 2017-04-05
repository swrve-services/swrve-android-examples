package com.swrve.usersampleid;

import com.swrve.sdk.SwrveHelper;
import com.swrve.sdk.SwrveSDK;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility to send a user_property update to Swrve. The user property should be a number
 * from 1-100 and allows is generated using thier Swrve User Id.
 * This number can then be used to separate users into target groups and compare different
 * marketing strategies.
 * E.g. To target 1/4 of users you would use the filter "user_sample_id between 1 and 25 inclusive"
 *
 * config.setUserId("01a94aec-d8ba-4292-8ab3-04edef4d1d71"); // returns 1
 * config.setUserId("018bb009-db2c-4feb-9c61-ec5871cd1cea"); // returns 50
 * config.setUserId("01b2d2e6-3fa5-4abb-aad8-d0aed0ece9b8"); // returns 100
 */

public class SwrveSampleIdUtils {

    public static void sendSampleIdForUser() {
        String userId = SwrveSDK.getUserId();

        // get md5 hash of the userId
        String hash = SwrveHelper.md5(userId);
        hash = hash.substring(0, 8);

        // get a numeric representation of the hash
        double userNumber = (double)Long.parseLong(hash, 16);
        final double maxMd5 = 4294967295.0;
        // divide by the maxMd5 to return a decimal between 0 and 1
        double userRange = userNumber / maxMd5;
        // From this decimal get an int from 1-100
        int userSampleId = (int) Math.ceil(userRange * 100.0d);

        // Update the user user_sample_id user property in Swrve
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("user_sample_id", String.valueOf(userSampleId));
        SwrveSDK.userUpdate(attributes);

        // Force the user update to send immediately so the user property
        // can be used as soon as possible to segment the user
        SwrveSDK.sendQueuedEvents();
    }
}