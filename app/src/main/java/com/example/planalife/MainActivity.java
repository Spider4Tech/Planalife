package com.example.planalife;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.planalife.databinding.ActivityMainBinding;
import com.example.planalife.ui.Calendar.CalendarFragment;
import com.example.planalife.ui.CalendarAdapter;
import com.example.planalife.ui.home.HomeFragment;
import com.example.planalife.ui.note.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        BottomNavigationView bottomBar = findViewById(R.id.bottomNavigationView);
        bottomBar.setSelectedItemId(R.id.navigation_home);

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    replaceFragment(new CalendarFragment());
                    break;
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_notifications:
                    replaceFragment(new testingFragment());
                    break;
            }

            return true;
        });





    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.replace(R.id.frame_out, fragment);
        fragmenttransaction.commit();

    }


}

