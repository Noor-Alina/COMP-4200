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

    @Query("DELETE FROM surveys")
    void deleteAllSurveys();

    @Query("SELECT * FROM surveys WHERE survey_id = :surveyId")
    surveyEntity getSurveyByLong(long surveyId);

    @Query("SELECT survey_id FROM surveys WHERE title = :surveyTitle")
    int getSurveyIdFromTitle(String surveyTitle);

    @Query("SELECT * FROM surveys WHERE userId = :userId")
    List<surveyEntity> getSurveysByUserId(String userId);

    @Query("SELECT * FROM surveys WHERE userId != :userId")
    List<surveyEntity> getSurveysWithoutMatchingUserId(String userId);

    @Query("SELECT COUNT(*) FROM surveys WHERE survey_id = :surveyId")
    int getResponseCount(int surveyId);


    @Update
    void updateSurvey(surveyEntity survey);

    @Delete
    void deleteSurvey(surveyEntity survey);

}
