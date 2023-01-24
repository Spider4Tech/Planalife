package com.example.planalife.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.planalife.R;
import com.example.planalife.databinding.FragmentHomeBinding;
import com.example.planalife.ui.CalendarAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    Button addButton;
    EditText inputText;
    ListView todoList;
    ArrayList<String> list;
    TodoAdapter todoAdapter;

    public void onAddItem(View view) {
        String text = inputText.getText().toString();

        if (!text.equals("")) {

            list.add(text);

            todoList.setAdapter(todoAdapter);

            inputText.setText("");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addButton = view.findViewById(R.id.save_button);
        inputText = view.findViewById(R.id.text_box);
        todoList = view.findViewById(R.id.listView);
        list = new ArrayList<>();

        todoAdapter = new TodoAdapter(list, getContext());
        todoList.setAdapter(todoAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (!inputText.getText().toString().equals("")) {
                    saveText();
                } else {
                    System.out.println(inputText.getText().toString().length());
                    System.out.println(inputText.getText().toString());
                }
                onAddItem(v);
            }

        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void saveText() {
        final int REQUEST_WRITE_STORAGE_PERMISSION = 1;


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE_PERMISSION);
        } else {
            String text = inputText.getText().toString();
            File file = new File(requireContext().getExternalFilesDir(null), "saved_text.txt");
            try {
                FileWriter writer = new FileWriter(file);
                writer.append(text);
                writer.flush();
                System.out.println("reg");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}