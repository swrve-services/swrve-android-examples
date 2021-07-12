In App Review Example
----------------------
This examples demonstrates how to target and trigger the [Google Play In-App Review API](https://developer.android.com/guide/playcore/in-app-review) using Swrve. 

This API may or may not show the rating dialog to the user when called so showing an interstitial before calling it wouldn't make sense.
This solution uses a [Swrve Embedded Campaign](https://docs.swrve.com/user-documentation/campaigns/campaign-builder/content/embedded-campaigns/) to trigger the API.

Prerequisites
--------------
- Android device runnint >= Android 5.0 (API 21)
- Google Play Core Library >= version 1.8.0 

How to Run this Demo in Android Studio
--------------------------------------
- Import AppStoreReview into Android Studio.
- Add YOUR_APP_ID in MyApplication.java with your Swrve app ID.
- Replace YOUR_API_KEY in MyApplication.java with your Swrve API key.
- Run AppStoreReview app normally.

Code Changes Required
----------------------
- See the changes required marked clearly in the MyApplication.java, MainActivity.java and MyBaseActivity.java files.
- The main change is to configure the SwrveEmbeddedMessageListener.
- Inside this listener you will have access to the campaign JSON string added in the Content of the Campaign in the Swrve Dashboard.
- In this example we check for a "campaign_type" parameter which should be set to "app_review". 
- If this is the case we call the Google Play In-App Review API.
- This is all done in the Application class before initializing Swrve. The Google Play In-App Review API requires the Activity context to be passed. This means we need to also add code to allow you to know which Activity is currently active from the Application class.

Dashboard Setup
----------------
- Create an Embedded campaign with the following JSON content:
```
{"campaign_type":"app_review"}
```
- Set a target audience and triggering point for the campaign.
- When the campaign is triggered a request to Google Play In-App Review API will be made.
- The dialog may or may not be shown - Google decide whether or not it should be shown.
