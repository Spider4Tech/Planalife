package com.example.planalife.ui.home;

import android.Manifest;
import android.content.Context;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    deleteLine(); // à virer en fin de dev
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

    public boolean deleteLine() {
        File file = new File(requireContext().getFilesDir(), "data.txt");
        if (file.exists()) {
            file.delete();
            System.out.println("delete");
        }
        return true;

    }

    private void saveText() {
        final int REQUEST_WRITE_STORAGE_PERMISSION = 1;


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE_PERMISSION);
        } else {
            String text = inputText.getText().toString();
            File file = new File(requireContext().getFilesDir(), "data.txt");
            try {
                FileInputStream fis = getContext().openFileInput("data.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                    System.out.println(line);
                }
                fis.close();

                //File file = new File(requireContext().getFilesDir(), "data2.txt");
                System.out.println(requireContext().getFilesDir());
                FileWriter writer = new FileWriter(file, false);
                sb.append(text).append("\n");
                writer.append(sb.toString());
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();

                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException et) {
                        et.printStackTrace();
                    }
                }
            }
        }
    }
}