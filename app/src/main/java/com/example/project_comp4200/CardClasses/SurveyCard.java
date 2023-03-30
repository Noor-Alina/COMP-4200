package com.example.project_comp4200.CardClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_comp4200.ActivityClasses.Survey;
import com.example.project_comp4200.R;

public class SurveyCard extends CardView {
    public TextView titleView;
    public TextView descriptionView;

    public SurveyCard(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_survey_card, this, true);

        titleView = findViewById(R.id.surveyTitle);
        descriptionView = findViewById(R.id.surveyDescription);

        // Add the click listener
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Survey.class);
                intent.putExtra("surveyTitle", getSurveyTitle());
                context.startActivity(intent);
            }
        });
    }

    public void setSurveyTitle(String title) {
        titleView.setText(title);
    }

    public String getSurveyTitle(){ return titleView.getText().toString(); }

    public void setSurveyDescription(String description) {
        descriptionView.setText(description);
    }
}