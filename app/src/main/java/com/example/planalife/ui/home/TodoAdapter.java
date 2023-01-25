package com.example.planalife.ui.home;

import android.app.AlertDialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.planalife.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    private ArrayList todoList;
    private LayoutInflater inflater;

    public TodoAdapter(ArrayList todoList, Context context) {
        this.todoList = todoList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            FileInputStream fis = context.openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                todoList.add(line);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        completeButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final int selectedItemPosition = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirmation de suppression");
                builder.setMessage("Êtes-vous sûr de vouloir supprimer cet élément?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(selectedItemPosition);
                        todoList.remove(selectedItemPosition);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


                return true; // retournez true pour indiquer que l'événement a été géré
            }
        });

        return view;
    }
}