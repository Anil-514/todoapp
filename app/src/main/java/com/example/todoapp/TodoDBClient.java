package com.example.todoapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;


@Database(entities = {Todo.class}, version  = 1, exportSchema = false)
public abstract class TodoDBClient extends RoomDatabase {

    private static final Object LOCK = new Object();
    private Context mCtx;
    private static String DATABASE_NAME = "tododb";
    private static TodoDBClient mInstance;
    public abstract TodoDao todoDao();
    public static TodoDBClient getInstance(Context mCtx) {
        if(mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(mCtx.getApplicationContext(),
                        TodoDBClient.class, TodoDBClient.DATABASE_NAME).build();
            }
        }
        return mInstance;
    }
}
