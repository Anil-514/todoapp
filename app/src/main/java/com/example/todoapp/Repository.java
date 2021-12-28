package com.example.todoapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Repository {

    private  static Repository sInstance;

    private ArrayList<Todo> todos;

    private  Repository(Context context) {
//        class GetTodos extends AsyncTask<Void,Void,Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                List<Todo> todoList = TodoDBClient.getInstance(context.getApplicationContext()).todoDao().getAll();
////                Log.d("todoList:",todoList.toString());
//                for (int i = 0; i < todoList.size(); i++) {
//
//                    Todo todo = new Todo();
//                    todo.setTitle("title" + i);
//                    todo.setDescription("descrption" + i);
//                    todo.setPriority(1);
//                    todos.add(todo);
//                }
//                return null;
//            }
//        }
//        todos = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Todo todo = new Todo();
//            todo.setTitle("title" + i);
//            todo.setDescription("descrption" + i);
//            todo.setPriority(1);
//            todos.add(todo);
//        }
    }

    public static Repository getInstance(Context context){
        if(sInstance == null){
            sInstance = new Repository(context);
        }
            return sInstance;
    }

    public List<Todo> getAllTodos(){
        return todos;
    }

    public Todo getTodoById(int id){
//        for (int i = 0; i < todos.size(); i++) {
//            Todo todo = todos.get(i);
//            if(todo.getId().equals(id)){
//                return todo;
//            }
//        }
        return null;
    }

    public void delete(int id){
       Todo todo = getTodoById(id);
       if(todo != null)
        todos.remove(todo);
    }

    public void addTodo(Todo todo){
        todos.add(todo);
    }

    public Todo update(Todo todo){
        Todo newTodo = getTodoById(todo.getId());
        newTodo.setTitle(todo.getTitle());
        newTodo.setDescription(todo.getDescription());
        newTodo.setPriority(todo.getPriority());
        Date date = new Date();
        Utils ut = new Utils();
        newTodo.setUpdatedAt(ut.DateFormater(date));
        return newTodo;
    }


}
