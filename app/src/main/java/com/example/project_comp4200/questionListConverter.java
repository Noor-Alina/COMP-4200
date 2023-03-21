package com.example.project_comp4200;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class questionListConverter {

    @TypeConverter
    public String fromQuestionList(List<questionEntity> questions) {
        Gson gson = new Gson();
        return gson.toJson(questions);
    }

    @TypeConverter
    public List<questionEntity> toQuestionList(String data) {
        Type listType = new TypeToken<List<questionEntity>>() {}.getType();
        return new Gson().fromJson(data, listType);
    }
}
