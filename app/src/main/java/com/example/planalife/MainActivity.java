package com.example.planalife;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.planalife.databinding.ActivityMainBinding;
import com.example.planalife.ui.Calendar.CalendarFragment;
import com.example.planalife.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        // Initialisation de Google Play Game Services
//        GoogleSignIn.getClient(this,
//                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
//        GoogleSignInAccount mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

//        GamesClient mGamesClient = Games.getGamesClient(this, Objects.requireNonNull(mGoogleSignInAccount));
//        mGamesClient.setViewForPopups(findViewById(android.R.id.content));


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

