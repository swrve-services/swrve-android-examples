Splash Screen Example
-------------------------
When an Android app has a splash screen it can affect how In App Campaigns are shown at the start of a session. This can happen for In App Messages that trigger on app launch or for Push to In App campaigns. 
The issue happens when an In App Campaign is shown while the splash screen is showing. When the splash screen goes away the In App Message will sometimes dissappear too. To prevent this we need to add some logic to the splash screen activity to prevent it dismissing while another Activity is showing on top of it. 

How to Run this Demo in Android Studio
--------------------------------------
- Import SplashScreenExample into Android Studio.
- Add YOUR_APP_ID in MyApplication.java with your Swrve app ID.
- Replace YOUR_API_KEY in MyApplication.java with your Swrve API key.
- Run SplashScreenExample app normally.
- Create an In App Campaign triggered on app launch to show the solution working. 
- The Splash Screen should only be dismissed after the In App Campaign is dismissed.

How to Use This in Your Own App
-------------------------------
- In this solution the splash screen activity's onPause() method is used to set a flag when the Splash screen is overlayed by another activity
- canSwitchActivity = false. 
- The onResume() method is used to set the flag back to true when the activity becomes active again. 
- Instead of switching to the Main Activity at the normal time (e.g. after some background processing) the method maybeSwitchActivity() is called. 
- This method checks if canSwitchActivity is true. If true the activity can be switched as normal. 
- If canSwitchActivity is false the activity is not switched at that time.
- Instead maybeSwitchActivity() is called in the onResume() method which will be called when the In App Campaign is dismissed. 
- See this code in [SplashActivity.java](app/src/main/java/com/dbreen/splashscreenexample/SplashActivity.java)