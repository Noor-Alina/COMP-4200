package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class surveyResult extends AppCompatActivity {

    private TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);
        resultView = findViewById(R.id.Data_textView);


        //get survey id from the userMenu intent extras
       int surveyId = getIntent().getIntExtra("surveyId", -1);

            if (surveyId != -1) {
                //use survey id to query for the num of responses to the survey
                new FetchResponseCountTask().execute(String.valueOf(surveyId));
            }
    }

    private class FetchResponseCountTask extends AsyncTask<String, Void, Integer>{


        @Override
        protected Integer doInBackground(String... params) {
            String surveyId = params[0];
            AppDatabase appDatabase = AppController.getInstance().getAppDatabase();
            return appDatabase.surveyDAO().getResponseCount(Integer.valueOf(surveyId));
        }

        protected void onPostExecute(Integer responsCount){

              //add to text view the number of Responses
              resultView.setText("Responses: "+ responsCount);


        }
    }
}