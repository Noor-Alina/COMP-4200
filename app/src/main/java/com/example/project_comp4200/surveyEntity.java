package com.example.project_comp4200;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "surveys")
public class surveyEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "survey_id")
    public int survey_id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @TypeConverters(questionListConverter.class)
    @ColumnInfo(name = "questions")
    public List<questionEntity> questions;

    public surveyEntity(String title, String description) {
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    public int getId() {
        return survey_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<questionEntity> getQuestions() {
        return questions;
    }

    public void setId(int id) {
        this.survey_id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(List<questionEntity> questions) {
        this.questions = questions;
    }
}

