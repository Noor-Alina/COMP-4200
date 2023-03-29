package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class Survey extends AppCompatActivity {

    private AppDatabase appDatabase;
    private int surveyId;
    private String surveyTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        appDatabase = AppController.getInstance().getAppDatabase();

        Intent intent = getIntent();
        surveyTitle = intent.getStringExtra("surveyTitle");

        new LoadQuestionsTask().execute(surveyTitle);
    }

    private class LoadQuestionsTask extends AsyncTask<String, Void, List<questionEntity>> {
        int surveyId;

        @Override
        protected List<questionEntity> doInBackground(String... params) {
            String surveyTitle = params[0];

            surveyId = appDatabase.surveyDAO().getSurveyIdFromTitle(surveyTitle);
            if (surveyId == -1) {
                // handle error
                return null;
            }

            // query the database for all questions with the survey id
            return appDatabase.questionsDAO().getQuestionsForSurvey(surveyId);
        }

        @Override
        protected void onPostExecute(List<questionEntity> questions) {
            if (questions == null) {
                // handle error
                return;
            }

            // get a reference to the LinearLayout that will contain the questions
            LinearLayout questionCardsContainer = findViewById(R.id.question_cards_container);

            // loop through each question and create a card view to display it
            for (questionEntity question : questions) {
                // inflate the card view layout
                View questionCard = LayoutInflater.from(Survey.this).inflate(R.layout.question_card, questionCardsContainer, false);

                // set the question text
                TextView questionText = questionCard.findViewById(R.id.question_text);
                questionText.setText(question.getQuestionText());

                // add the options to a radio group
                RadioGroup optionsGroup = questionCard.findViewById(R.id.options_group);
                List<String> options = question.getOptions();
                int counter = 0;
                for (String option : options) {
                    RadioButton optionButton = new RadioButton(Survey.this);
                    System.out.println(counter + ": " + option);
                    optionButton.setText(option);
                    optionsGroup.addView(optionButton);
                    counter++;
                }

                // add the card view to the question cards container
                questionCardsContainer.addView(questionCard);
            }
        }

    }
}
