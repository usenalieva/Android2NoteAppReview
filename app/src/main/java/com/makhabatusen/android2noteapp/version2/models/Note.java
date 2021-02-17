package com.makhabatusen.android2noteapp.version2.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private long id;
    String note;
    String createdAt;

    // for FireStore
    public Note() {
    }

    public Note(String note, String createdAt) {
        this.note = note;
        this.createdAt = createdAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
