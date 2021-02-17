package com.makhabatusen.android2noteapp.version2.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.makhabatusen.android2noteapp.version2.models.Note;

@Database (entities = { Note.class}, version = 1)
 public abstract class AppDataBase  extends RoomDatabase {
    public  abstract NoteDao noteDao();
}




