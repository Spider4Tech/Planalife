package com.example.planalife.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.planalife.R;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    private ArrayList todoList;
    private LayoutInflater inflater;

    public TodoAdapter(ArrayList todoList, Context context) {
        this.todoList = todoList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.todo_detail, null);
        TextView todoText = view.findViewById(R.id.todoText);
        Button completeButton = view.findViewById(R.id.completeButton);

        completeButton.setText("en cours");
        todoText.setText(todoList.get(position).toString());

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (completeButton.getText() == "effectué"){
                    completeButton.setText("en cours");
                }else {
                    completeButton.setText("effectué");
                }

            }
        });

        return view;
    }
}