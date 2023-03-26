package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class Survey extends AppCompatActivity {

    private AppDatabase appDatabase;
    private int surveyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        appDatabase = AppController.getInstance().getAppDatabase();

        Intent intent = getIntent();
        surveyId = intent.getIntExtra("survey_id", -1);

        if (surveyId == -1) {
            // handle error
        }

        // query the database for all questions with the survey id
        List<questionEntity> questions = appDatabase.questionsDAO().getQuestionsForSurvey(surveyId);

        // get a reference to the LinearLayout that will contain the questions
        LinearLayout questionsLayout = findViewById(R.id.questions_layout);

        // loop through each question and create a card view to display it
        for (questionEntity question : questions) {
            // inflate the card view layout
            View questionCard = LayoutInflater.from(this).inflate(R.layout.question_card, questionsLayout, false);

            // set the question text
            TextView questionText = questionCard.findViewById(R.id.question_text);
            questionText.setText(question.getQuestionText());

            // add the options to a radio group
            RadioGroup optionsGroup = questionCard.findViewById(R.id.options_group);
            List<String> options = question.getOptions();
            for (String option : options) {
                RadioButton optionButton = new RadioButton(this);
                optionButton.setText(option);
                optionsGroup.addView(optionButton);
            }

            // add the card view to the questions layout
            questionsLayout.addView(questionCard);
        }
    }
}