package com.example.project_comp4200;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface selectedOptionDAO {

    @Insert
    void insertSelectedOption(selectedOptionEntity selectedOption);

    @Update
    void updateSelectedOption(selectedOptionEntity selectedOption);

    @Delete
    void deleteSelectedOption(selectedOptionEntity selectedOption);

    @Query("SELECT * FROM selected_options WHERE question_id = :questionId")
    List<selectedOptionEntity> getSelectedOptionsForQuestion(long questionId);
}
