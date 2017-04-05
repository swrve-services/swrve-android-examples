Swrve SDK User Sample Id
-------------------------
This solution generates a number between 1 and 100 and assign it to each user as a user property. 
This number can then be used to sort users into different groups and then target them with different marketing strategies over multiple campaigns.

How to Run this Demo in Android Studio
--------------------------------------
- Import UserSampleID into Android Studio.
- Replace YOUR_APP_ID in SampleApplication.java with your Swrve app ID.
- Replace YOUR_API_KEY in SampleApplication.java with your Swrve API key.
- Run UserSampleId app normally.
- Add the device as a QA device to see the "user_sample_id" user property being sent to Swrve.

How to Use This in Your Own App
-------------------------------
- Copy the SwrveSampleIdUtils class into your own project.
- Call SwrveSampleUtils.sendSampleIdForUser() as soon as possible after calling Swrve.SDK.onCreate(this) in your first Activity.
- This method generates a number from 1-100 and then updates the "user_sample_id" user property with the value generated.
- This "user_sample_id" user_property can be used to target groups of users with different marketing strategies.