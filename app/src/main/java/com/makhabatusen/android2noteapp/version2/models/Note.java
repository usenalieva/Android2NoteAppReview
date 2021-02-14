package com.makhabatusen.android2noteapp.version2.models;

import java.io.Serializable;

public class Note implements Serializable {
    String note;
    String createdAt;

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
}
