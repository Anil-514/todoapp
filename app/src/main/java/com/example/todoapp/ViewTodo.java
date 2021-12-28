package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Update;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ViewTodo extends AppCompatActivity {
    private EditText todoTitle;
    private EditText todoDesc;
    private Button updateButton;
    private int adapterPosition;
    private int mId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);
        todoTitle = findViewById(R.id.todoTitleEditText);
        todoDesc = findViewById(R.id.todoDescEditText);
        updateButton = findViewById(R.id.updateTodoButton);
        // retrieving Extras From Intent
        Bundle extras = getIntent().getExtras();
        todoTitle.setText(extras.getString("title"));
        todoDesc.setText(extras.getString("desc"));
        mId = extras.getInt("id");
        adapterPosition = extras.getInt("adapterPosition");
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTodo();
            }
        });
    }

    public void DeleteTodo(MenuItem item){
        deleteTodoById();
    }

    private void deleteTodoById() {
        final int dID = mId;
        class DeleteToByID extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                TodoDBClient.getInstance(getApplicationContext()).todoDao().delete(dID);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(),"Successfully Deleted Todo",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.putExtra("adapterPosition",adapterPosition);
                setResult(2,intent);
                finish();
            }
        }
        DeleteToByID dt = new DeleteToByID();
        dt.execute();
    }

    private void updateTodo() {
        final String sTitle = todoTitle.getText().toString().trim();
        final String sDesc = todoDesc.getText().toString().trim();

        class UpdateTodo extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
//                Todo todo = new Todo();
//                todo.setTitle(sTitle);
//                todo.setDescription(sDesc);
//                todo.setPriority(1);
                Date date = new Date();
                Utils ut = new Utils();
//                todo.setUpdatedAt(ut.DateFormater(date));
                TodoDBClient.getInstance(getApplicationContext()).todoDao().update(sTitle, sDesc,ut.DateFormater(date),mId);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Successfully updated todo", Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                setResult(2,intent);
                finish();
            }
        }
        UpdateTodo ut = new UpdateTodo();
        ut.execute();
    }



}