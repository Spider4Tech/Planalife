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
        System.out.println(todoList.get(position).toString());
        todoText.setText(todoList.get(position).toString());

//        if (ContextCompat.checkSelfPermission(getContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE_PERMISSION);
//        } else {
//            String text = textBox.getText().toString();
//            File file = new File(requireContext().getExternalFilesDir(null), "saved_text.txt");
//            try {
//                FileWriter writer = new FileWriter(file);
//                writer.append(text);
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

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