# Android Latitude and Longitude Picker

Android code to display a location picker. The user can select a point on the map, and the code returns the latitude and longitude value, and the name of the place according to Google Map.

(For the time being this project works as a wrapper for the jQuery latitude and longitude picker project. Read about it more: [github.com/wimagguc/jquery-latitude-longitude-picker-gmaps](https://github.com/wimagguc/jquery-latitude-longitude-picker-gmaps))


## Screenshot
[!Screenshot](https://raw.githubusercontent.com/rafi-kamal/android-latitude-longitude-picker/master/screenshot.png)

## Install

Simply create an Android project and add the following files:

* com/wimagguc/locationpicker/LocationPickerActivity.java
* assets/locationPicker/*
* res/layout/activity_location_picker.xml
* res/menu/location_picker.xml
* res/drawable-*

Then modify the AndroidManifest.xml to include this activity:

```xml
<application>
  <activity
     android:name="com.wimagguc.locationpicker.LocationPickerActivity"
     android:uiOptions="splitActionBarWhenNarrow" />
</application>
```

See `cubi/rafi/locationpickertest/MainActivity` for exmaple usage.

## Compatibility

The app is should work on any Android device with API level 11+. To use it in API 8+, use Actionbar Sherlock/Appcompat and modify files accordingly (for example, replace `Activity` with `SherlockActivity`, fix `Menu` and `MenuItem` imports etc.).

## License

Do with the code whatever you please.

This code uses the jQuery Javascript library and the Google Maps API. To read more about these, go to: [jquery.com/](http://jquery.com/) and [developers.google.com/maps/](https://developers.google.com/maps/)

## Used at

* [Dive Log](https://play.google.com/store/apps/details?id=com.divespy.android&hl=en) Android app

(If you are using this code for another project, please add it here - or just let me know and it will be featured here.)

## About

Richard Dancsi
[www.wimagguc.com](http://www.wimagguc.com/)

twitter: [@wimagguc](http://twitter.com/wimagguc)
linkedin: [linkedin.com/in/richarddancsi](http://linkedin.com/in/richarddancsi)
gplus: [plus.google.com/u/0/115939246085616544919](https://plus.google.com/u/0/115939246085616544919) 


Rafi Kamal
[rafi-kamal.com](http://rafi-kamal.com/)

linkedin: [linkedin.com/in/rafikamal](http://linkedin.com/in/rafikamal)
gplus: [plus.google.com/+RafiKamal](https://plus.google.com/+RafiKamal) 
