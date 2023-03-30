package com.example.project_comp4200.Database;

import android.app.Application;

import com.example.project_comp4200.Database.AppDatabase;

public class AppController extends Application {

    private static AppController instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate(){
        // creates an instance of the database on creation of application whilst incorporating the original oncreate method
        super.onCreate();
        instance = this;
        appDatabase = AppDatabase.getInstance(this);
    }

    public static AppController getInstance(){
        return instance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
