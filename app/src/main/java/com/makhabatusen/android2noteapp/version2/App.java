package com.makhabatusen.android2noteapp.version2;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.makhabatusen.android2noteapp.version2.room.AppDataBase;
import com.makhabatusen.android2noteapp.version2.utils.Prefs;

public class App extends Application {
    private  static Prefs prefs;
    private static AppDataBase appDatabase;

    public static Prefs getPrefs() {
        return prefs;
    }

    public static AppDataBase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(this,
                AppDataBase.class,
                "database")
                .allowMainThreadQueries()
                .build();
        prefs = new Prefs(this);
    }

}
