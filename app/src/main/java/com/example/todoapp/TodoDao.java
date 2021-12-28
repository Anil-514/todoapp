package com.example.todoapp;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;
@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo")
    List<Todo>getAll();

    @Insert
    void insert(Todo todo);


    @Query("Delete from todo where id=:id")
    void delete(int id);

    @Query("Update todo set title = :title, description = :description, update_at = :updateAt where id = :id")
    void update(String title, String description, String updateAt, int id);

    @Query("Select * from todo where id=:id")
    List<Todo> loadTodoById(int id);
}
