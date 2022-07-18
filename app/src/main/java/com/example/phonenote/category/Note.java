package com.example.phonenote.category;

public class Note {

    private int id;

    private String title;

    private String content;

    public Note(String title, String content,int id){
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public int getId(){
        return id;
    }
}