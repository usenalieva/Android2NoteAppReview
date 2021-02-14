package com.makhabatusen.android2noteapp.version2;

import android.app.Application;

import com.makhabatusen.android2noteapp.version2.utils.Prefs;

public class App extends Application {
    private  static Prefs prefs;


    public static Prefs getPrefs() {
        return prefs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
    }
}
