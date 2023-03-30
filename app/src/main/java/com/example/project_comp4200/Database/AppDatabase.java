package com.example.project_comp4200.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project_comp4200.DAOFiles.questionsDAO;
import com.example.project_comp4200.DAOFiles.selectedOptionDAO;
import com.example.project_comp4200.DAOFiles.surveyDAO;
import com.example.project_comp4200.DBEntityFiles.questionEntity;
import com.example.project_comp4200.DBEntityFiles.selectedOptionEntity;
import com.example.project_comp4200.DBEntityFiles.surveyEntity;

@Database(entities = {surveyEntity.class, questionEntity.class, selectedOptionEntity.class}, version = 3)
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

    public void deleteAllSurveysAndQuestions() {
        // Run the database operation on a background thread
        new Thread(() -> {
            surveyDAO().deleteAllSurveys();
            questionsDAO().deleteAllQuestions();
        }).start();
    }
}
