package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateSurvey extends AppCompatActivity {

    EditText surveyTitle;
    EditText surveyDesc;

    EditText question;
    Button buttonAddQuestion;

    Button buttonPostSurvey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        surveyTitle = findViewById(R.id.surveyTitleEditText);
        surveyDesc = findViewById(R.id.surveyDescEditText);
        question = findViewById(R.id.questionEditText);
        buttonAddQuestion = findViewById(R.id.addQuestionButton);
        buttonPostSurvey = findViewById(R.id.postSurveyButton);



    }
}