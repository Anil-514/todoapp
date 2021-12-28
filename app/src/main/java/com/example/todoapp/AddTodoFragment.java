package com.example.todoapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTodoFragment#} factory method to
 * create an instance of this fragment.
 */
public class AddTodoFragment extends Fragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button addButton;
    private Repository repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AddTodoFragment newInstance() {
        AddTodoFragment fragment = new AddTodoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_todo, container, false);
        titleEditText = view.findViewById(R.id.title_et);
        descriptionEditText = view.findViewById(R.id.description_et);
        addButton = view.findViewById(R.id.add_btn);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = Repository.getInstance(getActivity().getApplicationContext());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTodo();
            }
        });

    }

    private void saveTodo() {
        final String sTitle = titleEditText.getText().toString().trim();
        final String sDesc = descriptionEditText.getText().toString().trim();

        class SaveTodo extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Todo todo = new Todo();
                todo.setTitle(sTitle);
                todo.setDescription(sDesc);
                todo.setPriority(1);
                Date date = new Date();
                Utils ut = new Utils();
                todo.setUpdatedAt(ut.DateFormater(date));
                TodoDBClient.getInstance(getContext()).todoDao().insert(todo);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getContext().getApplicationContext(), "SuccessFully Saved Todo", Toast.LENGTH_LONG).show();
            }
        }
        SaveTodo st = new SaveTodo();
        st.execute();
    }
}