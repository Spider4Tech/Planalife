package com.example.planalife;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.planalife.databinding.ActivityMainBinding;
import com.example.planalife.ui.Calendar.CalendarFragment;
import com.example.planalife.ui.home.HomeFragment;
import com.example.planalife.ui.note.NotificationsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    System.out.println("calendar");
                    replaceFragment(new CalendarFragment());
                    break;
                case R.id.navigation_home:
                    System.out.println("home");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_notifications:
                    System.out.println("note");
                    replaceFragment(new NotificationsFragment());
                    break;
            }

            return true;
        });

    }



    private void replaceTestingFragment(testingFragment fragment, Fragment frag){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.replace(R.id.frame_out, frag);
        fragmenttransaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.replace(R.id.frame_out, fragment);
        fragmenttransaction.commit();
    }




}