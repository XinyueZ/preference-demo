# Demo. Shows how build preference in Android

### What

The preference or Setting etc, you know what I mean and what they can do in your Android App.

### Official info

You can ignore this repository if you only want to read Google guides about preference programming, by clicking [here](http://developer.android.com/guide/topics/ui/settings.html).

### Design concept

##### Prior to Android 3.0

A ***one sheet***  which almost all preference options, this design has been deprecated and replaced by the ***master/slave*** or ***headers/sheet*** pattern.

##### After Android 3.0

A ***master/slave*** or ***headers/sheet***   pattern, which means we list a summary headers on the main preference screen and user can do selection to any of them to operate on ***one sheet*** under header.

### Programming

1. A preference layout for ***one sheet*** or ***headers/sheet***
    * preference.xml, actually you can use any to name.

       A ***one sheet*** layout with [PreferenceScreen](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceScreen.html)

       ```xml
         <PreferenceScreen
                  xmlns:android="http://schemas.android.com/apk/res/android"
                  >
                  <CheckBoxPreference
                   android:key="preference_save_data"
                   android:title="Do you want to save data"
                   android:summary="Save your application data if you checked"
                   android:defaultValue="true"
                   />
      </PreferenceScreen>
       ```

       A ***one sheet*** layout with [v7 PreferenceScreen](http://developer.android.com/intl/zh-cn/reference/android/support/v7/preference/PreferenceScreen.html)

       ```xml
         <android.support.v7.preference.PreferenceScreen
                  xmlns:android="http://schemas.android.com/apk/res/android" >
        <android.support.v7.preference.CheckBoxPreference
                   android:key="preference_save_data"
                   android:title="Do you want to save data"
                   android:summary="Save your application data if you checked"
                   android:defaultValue="true"
                   />
      </android.support.v7.preference.PreferenceScreen>

       ```
       ***or*** A list of headers
       ```xml
       <preference-headers xmlns:android="http://schemas.android.com/apk/res/android" >
      	<header
      		android:title="Data save"
      		android:summary="Option of saving data of application."
      		android:fragment="com.example.SaveDataFragment">
        		<extra android:name="index" android:value="0"/>
            <extra android:name="header" android:value="preferences_save_data"/>
            <extra android:name="title" android:value="Save data"/>
	      </header>
        <header
      		android:title="Delete cache"
      		android:summary="Clean application cache."
      		android:fragment="com.example.CleanCacheFragment">
        		<extra android:name="index" android:value="1"/>
            <extra android:name="header" android:value="preferences_clear_cache"/>
            <extra android:name="title" android:value="Clean cache"/>
	      </header>
      </preference-headers>
       ```
    *  Using attributes in xml are easy to understand, they are real easy just like what they are :) . One thing interesting is "android:fragment" which declares a [fragment](http://developer.android.com/reference/android/app/Fragment.html) to display a ***Sheet***  when user select an option on ***master***. I reiterate this in the late doc.

2. Showing layout

    * [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) shows a hierarchy of preferences to the user. Prior to Android 3.0 this class only allowed the display of a single ***sheet*** of preference, a single [PreferenceScreen](http://developer.android.com/reference/android/preference/PreferenceScreen.html); this functionality should now be found in the new [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) class, which means the [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) contains list of [Header](http://developer.android.com/reference/android/preference/PreferenceActivity.Header.html)s and [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) shows a hierarchy of preferences to the user. **That is why you see "android:fragment"s above which can server for each [Header](http://developer.android.com/reference/android/preference/PreferenceActivity.Header.html) to open detail of it.**

    * [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) shows a hierarchy of Preference objects as lists like [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) above. This class can work with general [activities](http://developer.android.com/reference/android/app/Activity.html) like a normal [Fragment](http://developer.android.com/reference/android/app/Fragment.html) in order to take over ***sheet*** jobs and more this class was added after Android 3.0 for the ***master/slave*** or ***headers/sheet***. The [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) will be committed into  [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html)  automatically when the user selected a  [Header](http://developer.android.com/reference/android/preference/PreferenceActivity.Header.html) on the header-list. The  [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) will generate an [Intent](http://developer.android.com/reference/android/content/Intent.html) to info [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) what preferences should be used by calling addPreferencesFromXXX functions.

3. Standard or v7-support

  As mentioned before there are two versions of preferences on Android, you can use platform native or some from v7-support, except [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) (But you can create one [AppCompatPreferenceActivity](https://android.googlesource.com/platform/development/+/master/samples/Support7Demos/src/com/example/android/supportv7/app/AppCompatPreferenceActivity.java) )almost all elements have v7,i.e android.support.v7.preference.PreferenceScreen and PreferenceScreen. What special are fragments like  [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) and [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) which provide a little different features.

4. [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) and [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html)

  * Shared

    - All fragments  can show a hierarchy of Preference objects as lists.
    - All fragments  can work with any [activities](http://developer.android.com/reference/android/app/Activity.html).
    - You can use support-elements without package-name in xml for [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html), they would be converted to node with package-names automatically while building.

  * Diff

    - [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) works with standard components like PreferenceScreen, CheckBoxPreference etc.

    - [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html) enables to work with [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) to realize ***master/slave***  automatically. What you have to do is to define header-list and the "android:fragment"s that point different [PreferenceFragment](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceFragment.html)s to show a ***sheet***([PreferenceScreen](http://developer.android.com/reference/android/preference/PreferenceScreen.html))

    - [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) works with components like android.support.v7.preference.PreferenceScreen, android.support.v7.preference.CheckBoxPreference etc. There's no way to make ***master/slave*** or ***headers/sheet*** with [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) even if you use "app:fragment" instead "android:fragment". A crash will be fired if "android:fragment" points to a [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) .

    - Use [v7 PreferenceScreen](http://developer.android.com/intl/zh-cn/reference/android/support/v7/preference/PreferenceScreen.html) and [v7 Preference](http://developer.android.com/intl/zh-cn/reference/android/support/v7/preference/Preference.html) to let  [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) build a "header-list" and assign "app:fragment"s to point target [PreferenceFragmentCompat](http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html) as "sheet". You might create two different layouts for phone and tablet which is done by [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) automatically when you want ***master/slave*** or ***headers/sheet*** .

5. ActionBar or Toolbar

    * Generally there's a default theme works with  [PreferenceActivity](http://developer.android.com/intl/zh-cn/reference/android/preference/PreferenceActivity.html) which can or can't provide [ActionBar](http://developer.android.com/intl/zh-cn/reference/android/app/ActionBar.html), it depends on what kind of app-theme you define. However thank for the new  ```Theme.AppCompat ```, we can create an [AppCompatPreferenceActivity](https://android.googlesource.com/platform/development/+/master/samples/Support7Demos/src/com/example/android/supportv7/app/AppCompatPreferenceActivity.java) class to use [support-actionbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/app/ActionBar.html).

    * Use ```Theme.AppCompat ``` as theme on the [AppCompatPreferenceActivity](https://android.googlesource.com/platform/development/+/master/samples/Support7Demos/src/com/example/android/supportv7/app/AppCompatPreferenceActivity.java) you can get [support-actionbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/app/ActionBar.html), however it's difficult to use the [Toolbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/widget/Toolbar.html) with ```Theme.AppCompat.NoActionBar ``` when you don't use some [tricks](http://stackoverflow.com/questions/26564400/creating-a-preference-screen-with-support-v21-toolbar). The problem is that you can not write standard codes to locate the  [Toolbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/widget/Toolbar.html).

    * Use both fragments to work with [app-compat activities](http://developer.android.com/intl/zh-cn/reference/android/support/v7/app/AppCompatActivity.html) is fine and both ```Theme.AppCompat ```, ```Theme.AppCompat.NoActionBar ``` run  compatible  along with [support-actionbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/app/ActionBar.html) or [Toolbar](http://developer.android.com/intl/zh-cn/reference/android/support/v7/widget/Toolbar.html).


```

                      The MIT License (MIT)

                Copyright (c) 2016 Chris Xinyue Zhao

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
```

