package com.example.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public String DateFormater(Date date){
        String newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return newDate;
    }
}
