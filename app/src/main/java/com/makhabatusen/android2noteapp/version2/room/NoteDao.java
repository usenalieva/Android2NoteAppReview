package com.makhabatusen.android2noteapp.version2.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.makhabatusen.android2noteapp.version2.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);
}
