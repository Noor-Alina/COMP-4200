package com.example.project_comp4200;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {surveyEntity.class, questionEntity.class, selectedOptionEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract surveyDAO surveyDAO();

    public abstract questionsDAO questionsDAO();

    public abstract selectedOptionDAO selectedOptionDAO();

    public static AppDatabase databaseInstance;

    public static synchronized AppDatabase getInstance(Context context){

        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "App_Database").fallbackToDestructiveMigration().build();
        }

        return databaseInstance;
    }
}
