package com.example.socialmediaapp.Models;

import android.widget.EditText;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class PostModels {

     String docId;
     EditText title;
     EditText description;
     String ImageUrl;


     @ServerTimestamp
     Date date;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public EditText getTitle() {
        return title;
    }

    public void setTitle(EditText title) {
        this.title = title;
    }

    public EditText getDescription() {
        return description;
    }

    public void setDescription(EditText description) {
        this.description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
