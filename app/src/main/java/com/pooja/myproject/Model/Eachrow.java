package com.pooja.myproject.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pooja on 1/21/2017.
 */

public class Eachrow implements Comparable<Eachrow>
{
    String title,description,date;
    int image;
    int id;
    Date mydate;


    public Eachrow(int id, String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id=id;
    }

    public Eachrow(String title)
    {
        this.title=title;
    }

    public Date convertDate(String date)
    {
      java.util.Date mydate = null;
        try {
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        mydate = formatter.parse(date);
             } catch (ParseException e)
        {
           e.printStackTrace();
        }
        return mydate;
      }


    public Date sendDate()
    {
        mydate=convertDate(date);
        return mydate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    @Override
    public int compareTo(Eachrow eachrow) {
        return sendDate().compareTo(eachrow.sendDate());
    }
}
