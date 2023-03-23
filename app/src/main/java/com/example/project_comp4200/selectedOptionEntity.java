package com.example.project_comp4200;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity(tableName = "selected_options",
        foreignKeys = @ForeignKey(entity = questionEntity.class,
                parentColumns = "id",
                childColumns = "question_id",
                onDelete = ForeignKey.CASCADE))
public class selectedOptionEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "selected_option_id")
    private long id;

    @ColumnInfo(name = "question_id")
    private long questionId;

    @ColumnInfo(name = "option_text")
    private String optionText;

    public selectedOptionEntity(long questionId, String optionText) {
        this.questionId = questionId;
        this.optionText = optionText;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}