package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenu extends AppCompatActivity {

    Button createSurveyBtn;
    Button viewSurveysBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        createSurveyBtn = findViewById(R.id.createSurveyBtn);

        createSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createSurveyIntent = new Intent(UserMenu.this, CreateSurvey.class);
                startActivity(createSurveyIntent);
            }
        });


    }
}