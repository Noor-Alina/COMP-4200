package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateSurvey extends AppCompatActivity {

    EditText surveyTitle;
    EditText surveyDescription;
    EditText surveyNumQuestions;

    Button surveyCreationBtn;
    long surveyId;

    private final Executor executor = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        surveyTitle = findViewById(R.id.survey_title);
        surveyDescription = findViewById(R.id.survey_description);
        surveyNumQuestions = findViewById(R.id.numQuestions);
        surveyCreationBtn = findViewById(R.id.surveyCreateBtn);

        // getting the instance of created db
        AppDatabase appDatabase = AppController.getInstance().getAppDatabase();


        surveyCreationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // verifying that all fields have been filled out
                if(verifyFields()){
                    int numOfQuestions = Integer.parseInt(surveyNumQuestions.getText().toString());

                    // here the survey will be added to table and we will move to the question creation screen
                    Intent createQuestionIntent = new Intent(CreateSurvey.this, CreateQuestions.class);
                    createQuestionIntent.putExtra("numQuestions", numOfQuestions);

                    // Create a surveyEntity object
                    surveyEntity newSurvey = new surveyEntity(
                            surveyTitle.getText().toString(),
                            surveyDescription.getText().toString()
                    );

                    // Insert the survey into the database on a background thread
                    executor.execute(() -> {

                        try{
                            surveyId = appDatabase.surveyDAO().insertSurvey(newSurvey);
                        }catch(Exception e){
                            System.out.println("Error occurred" + e);
                        }


                        // code to be ran on main ui thread
                        runOnUiThread(() -> {
                            createQuestionIntent.putExtra("surveyID", surveyId);
                            startActivity(createQuestionIntent);
                        });
                    });

                }
            }
        });

    }

    public boolean verifyFields(){

        // store current edit text values in a string for ease of access
        String currentTitle = surveyTitle.getText().toString();
        String currentDescription = surveyDescription.getText().toString();
        String currentNumQuestions = surveyNumQuestions.getText().toString();

        if(currentTitle.matches("")){
            Toast.makeText(this, "You did not enter a survey title!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(currentDescription.matches("")){
            Toast.makeText(this, "You did not enter a survey description!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(currentNumQuestions.matches("")){
            Toast.makeText(this, "You did not enter the number of questions!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}