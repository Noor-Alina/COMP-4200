package com.example.project_comp4200;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface questionsDAO {

    @Insert
    void insertQuestion(questionEntity question);

    @Query("SELECT * FROM questions WHERE surveyid = :surveyId")
    List<questionEntity> getQuestionsForSurvey(int surveyId);

    @Query("SELECT * FROM questions WHERE id = :questionId")
    questionEntity getQuestionById(int questionId);


    @Update
    void updateQuestion(questionEntity question);

    @Delete
    void deleteQuestion(questionEntity question);
}
