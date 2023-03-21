package com.example.project_comp4200;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface surveyDAO {

    @Insert
    long insertSurvey(surveyEntity survey);

    @Query("SELECT * FROM surveys")
    List<surveyEntity> getAllSurveys();

    @Query("SELECT * FROM surveys WHERE survey_id = :surveyId")
    surveyEntity getSurveyByLong(long surveyId);

    @Update
    void updateSurvey(surveyEntity survey);

    @Delete
    void deleteSurvey(surveyEntity survey);

}
