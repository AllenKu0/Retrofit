package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Albums {

    private int userId;
    private int id;
    private String title;

    @SerializedName("body")             //會去對應json內body的值，給下面的text
    private String text;                //可以改成body，上面//掉會過

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}