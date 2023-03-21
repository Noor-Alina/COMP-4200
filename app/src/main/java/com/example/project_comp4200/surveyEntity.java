package com.example.project_comp4200;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "survey")
public class surveys {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @TypeConverters(QuestionListConverter.class)
    @ColumnInfo(name = "questions")
    public List<QuestionEntity> questions;
}

public class questions
