package com.example.todoapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodosListFragment#} factory method to
 * create an instance of this fragment.
 */
public class TodosListFragment extends Fragment {


    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private Repository repository ;
    private List<Todo> todos;
    private FloatingActionButton fabButton;
    private int mPos = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_todos_list, container, false);
       fabButton = view.findViewById(R.id.fab_button);
       recyclerView = view.findViewById(R.id.todos_list);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = Repository.getInstance(getActivity().getApplicationContext());

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoFragment fragment = AddTodoFragment.newInstance();
                FragmentManager fm  = getParentFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

            }
        });
//        todos = repository.getAllTodos();
//        adapter = new TodoAdapter(todos);
//        recyclerView.setAdapter(adapter);


        QueryTodos();
    }


    public void QueryTodos(){
        class GetTodos extends AsyncTask<Void,Void,List<Todo>> {

            @Override
            protected List<Todo> doInBackground(Void... voids) {
                List<Todo> todoList = TodoDBClient.getInstance(getContext().getApplicationContext()).todoDao().getAll();
                return todoList;
            }

            @Override
            protected void onPostExecute(List<Todo> todos) {
                super.onPostExecute(todos);

                adapter = new TodoAdapter(todos,getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            }
        }
        GetTodos gt = new GetTodos();
        gt.execute();
    }

}