Swrve SDK User Sample Id
------------------------
Example of how to implement [UserSampleIdUtils](src/main/java/com/swrve/usersampleid/UserSampleIdUtils.java) class which allows you to generate a number from 1-100 from the User Id and send it to 

Android Studio build instructions
---------------------------------
- Import UserSampleID into Android Studio.
- Replace YOUR_APP_ID in SampleApplication.java with your Swrve app ID.
- Replace YOUR_API_KEY in SampleApplication.java with your Swrve API key.
- Replace YOUR_SENDER_ID in SampleApplication.java with your Google Cloud Messaging Sender ID if you want to do push notifications.
- Run UserSampleId app normally.

Overview
--------

This solution allows you to generate a number between 1 and 100 and assign it to each user as a user property.
This number can then be used to sort users into different groups (or Swimlanes) and then target them with different marketing over multiple campaigns.