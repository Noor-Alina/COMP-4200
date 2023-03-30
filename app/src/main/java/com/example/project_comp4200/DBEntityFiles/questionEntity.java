package com.example.project_comp4200.DBEntityFiles;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.project_comp4200.Converters.optionListConverter;

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
    public long sid;

    @ColumnInfo(name = "question")
    public String questionText;

    @TypeConverters(optionListConverter.class)
    @ColumnInfo(name = "options")
    public List<String> options;

    public questionEntity(){}

    public questionEntity(String questionText, List<String> options, long surveyId) {
        this.questionText = questionText;
        this.options = options;
        this.sid = surveyId;
    }

    public int getId() {
        return qid;
    }

    public void setId(int id) {
        this.qid = id;
    }

    public long getSurveyId() {
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
