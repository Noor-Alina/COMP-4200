package com.example.project_comp4200;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "questions",
        foreignKeys = @ForeignKey(
                entity = surveyEntity.class,
                parentColumns = "survey_id",
                childColumns = "surveyid",
                onDelete = ForeignKey.CASCADE
        ))
public class questionEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int qid;

    @ColumnInfo(name = "surveyid")
    public int sid;

    @ColumnInfo(name = "question")
    public String questionText;

    @TypeConverters(optionListConverter.class)
    @ColumnInfo(name = "options")
    public List<String> options;

    public questionEntity(String questionText, List<String> options) {
        this.questionText = questionText;
        this.options = options;
    }

    public int getId() {
        return qid;
    }

    public void setId(int id) {
        this.qid = id;
    }

    public int getSurveyId() {
        return sid;
    }

    public void setSurveyId(int surveyId) {
        this.sid = surveyId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
