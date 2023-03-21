package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateQuestions extends AppCompatActivity {

    int totalNumberQuestions;

    EditText questionTitle;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;

    Button nextQuestionBtn;
    int questionCounter = 0;

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questions);

        Intent createSurveyIntent = getIntent();

        totalNumberQuestions = createSurveyIntent.getIntExtra("numQuestions", 0);

        int surveyID = createSurveyIntent.getIntExtra("surveyID", 0);

        // var instantiations
        questionTitle = findViewById(R.id.questionTitle);
        option1 = findViewById(R.id.option1Text);
        option2 = findViewById(R.id.option2Text);
        option3 = findViewById(R.id.option3Text);
        option4 = findViewById(R.id.option4Text);
        nextQuestionBtn = findViewById(R.id.nxtQuestionBtn);

        // get database instance
        AppDatabase appDatabase = AppController.getInstance().getAppDatabase();


        executor.execute(() -> {
            surveyEntity currentSurvey = appDatabase.surveyDAO().getSurveyById(surveyID);

            runOnUiThread(() -> {
                nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if all fields are changed
                        if(verifyEntries()){
                            ArrayList<String> options = new ArrayList<>();

                            // adding options to list
                            options.add(option1.getText().toString());
                            options.add(option2.getText().toString());
                            options.add(option3.getText().toString());
                            options.add(option4.getText().toString());

                            // creating new question
                            questionEntity newQuestion = new questionEntity(
                                    questionTitle.getText().toString(),
                                    options,
                                    surveyID
                            );

                            // Insert the question into the database on a background thread
                            executor.execute(() -> {
                                appDatabase.questionsDAO().insertQuestion(newQuestion);
                                // add the new question to the array list in the survey
                                currentSurvey.getQuestions().add(newQuestion);
                                // update survey row
                                appDatabase.surveyDAO().updateSurvey(currentSurvey);

                                // code to be ran on main ui thread
                                runOnUiThread(() -> {
                                    // increase question counter and reset text entries to be empty
                                    questionCounter++;
                                    option1.setText("");
                                    option2.setText("");
                                    option3.setText("");
                                    option4.setText("");
                                    questionTitle.setText("");
                                    if(questionCounter == totalNumberQuestions - 1){
                                        nextQuestionBtn.setText("Post");
                                    }else if(questionCounter == totalNumberQuestions){
                                        Intent returnToMenuIntent = new Intent(CreateQuestions.this, UserMenu.class);
                                    }
                                });
                            });

                        }
                    }
                });
            });
        });
        // get survey






    }

    public boolean verifyEntries(){
        // convert edittext views to strings
        String title, option1Text, option2Text, option3Text, option4Text;
        title = questionTitle.getText().toString();
        option1Text = option1.getText().toString();
        option2Text = option2.getText().toString();
        option3Text = option3.getText().toString();
        option4Text = option4.getText().toString();

        ArrayList<String> fields = new ArrayList<>();
        fields.add(title);
        fields.add(option1Text);
        fields.add(option2Text);
        fields.add(option3Text);
        fields.add(option4Text);
        // go through all fields and ensure they are not empty
        for(String field: fields){
            if(field.matches("")){
                Toast.makeText(this, "You did not enter text for all fields!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}