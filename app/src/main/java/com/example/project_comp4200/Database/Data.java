package com.example.project_comp4200.Database;

public class Data {

    private String surveyTitle;
    private String surveyDesc;

    public Data(String surveyTitle, String surveyDesc){
        this.surveyTitle = surveyTitle;
        this.surveyDesc = surveyDesc;
    }

    public String getSurveyTitle(){return surveyTitle;}

    public String getSurveyDesc(){return surveyDesc;}
}
