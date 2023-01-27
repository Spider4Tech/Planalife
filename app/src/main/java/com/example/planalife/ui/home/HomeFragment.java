package com.example.planalife.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.planalife.MainActivity;
import com.example.planalife.R;
import com.example.planalife.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    Button addButton, deleteButton;
    EditText inputText;
    ListView todoList;
    ArrayList<String> list;
    TodoAdapter todoAdapter;

    public void onAddItem(View view) {
        String text = inputText.getText().toString();

        if (!text.equals("") && text.length() <= 30) {

            list.add(text);
            todoList.setAdapter(todoAdapter);
            inputText.setText("");
        }
        else {
            Toast.makeText(getActivity(), "TODO trop long.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addButton = view.findViewById(R.id.save_button);
        deleteButton = view.findViewById(R.id.delete_button);
        inputText = view.findViewById(R.id.text_box);
        todoList = view.findViewById(R.id.listView);
        list = new ArrayList<>();

        todoAdapter = new TodoAdapter(list, getContext());
        todoList.setAdapter(todoAdapter);

        addButton.setOnClickListener(v -> {

            if (!inputText.getText().toString().equals("")) {
                saveText();

            } else {
                deleteAll(); //Todo à virer en fin de dev
            }
            onAddItem(v);
        });

        deleteButton.setOnClickListener(v -> {
            deleteAll();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public boolean deleteAll() {
        File file = new File(requireContext().getFilesDir(), "data.txt");
        if (file.exists()) {
            file.delete();
            System.out.println("delete");
            try {
                file.createNewFile();
            } catch (IOException et) {
                et.printStackTrace();
            }
            list.clear();
            todoAdapter.notifyDataSetChanged();
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
                }
                fis.close();

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