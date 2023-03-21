package com.example.project_comp4200;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class optionListConverter {

    @TypeConverter
    public String fromOptionList(List<String> options) {
        Gson gson = new Gson();
        return gson.toJson(options);
    }

    @TypeConverter
    public List<String> toOptionList(String data) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(data, listType);
    }
}
