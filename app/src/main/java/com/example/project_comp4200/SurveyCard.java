package com.example.project_comp4200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SurveyCard extends LinearLayout {
    public TextView titleView;
    public TextView descriptionView;

    public SurveyCard(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_survey_card, this, true);

        titleView = findViewById(R.id.survey_title);
        descriptionView = findViewById(R.id.survey_description);
    }

    public void setSurveyTitle(String title) {
        titleView.setText(title);
    }

    public void setSurveyDescription(String description) {
        descriptionView.setText(description);
    }
}