package com.pooja.myproject.Model;

/**
 * Created by Pooja on 1/23/2017.
 */

public class FinishedEachrow
{
    String title,date;
    int id;
    public FinishedEachrow(int id,String title, String date) {
        this.title = title;
        this.date = date;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
