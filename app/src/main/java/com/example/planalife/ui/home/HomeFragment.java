package com.example.planalife.ui.home;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.planalife.R;
import com.example.planalife.databinding.FragmentHomeBinding;
import com.example.planalife.ui.CalendarAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EditText textBox;
    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textBox = (EditText) view.findViewById(R.id.text_box);
        saveButton = (Button) view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });

        return view;
    }

    private void saveText() {
        String text = textBox.getText().toString();
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/saved_text.txt";

        File file = new File(fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.append(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}