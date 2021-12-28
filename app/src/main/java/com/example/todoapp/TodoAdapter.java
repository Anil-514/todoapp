package com.example.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.CustomViewHolder>{

    List<Todo> data;
    Context mCtx;

    public TodoAdapter(List<Todo> todos,Context context) {
        this.data = todos;
        this.mCtx = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Todo todo = data.get(position);
        holder.titleTextView.setText(todo.getTitle());
        holder.dateTV.setText(todo.getUpdatedAt());
        holder.setOnCustomItemCLickListener(new TodoClickListener() {
            @Override
            public void onCustomItemClickListener(View view, int pos) {
                Intent updateToDoIntent = new Intent(mCtx.getApplicationContext(), ViewTodo.class);
                updateToDoIntent.putExtra("title",todo.getTitle());
                updateToDoIntent.putExtra("desc",todo.getDescription());
                updateToDoIntent.putExtra("id",todo.getId());
                updateToDoIntent.putExtra("adapterPosition",holder.getAdapterPosition());
//                mCtx.startActivity(updateToDoIntent);
                ((Activity)mCtx).startActivityForResult(updateToDoIntent,2);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeAt(int position){
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,data.size());
    }


    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TodoClickListener customTodoItemClickListener = null;
        private TextView titleTextView;
        private TextView dateTV;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_tv);
            dateTV = itemView.findViewById(R.id.date_tv);
            itemView.setOnClickListener(this);
        }
        public void setOnCustomItemCLickListener(TodoClickListener todoClickListener) {
            this.customTodoItemClickListener = todoClickListener;
        }
        @Override
        public void onClick(View v) {
            this.customTodoItemClickListener.onCustomItemClickListener(v,getAdapterPosition());
        }
    }
}
