package com.example.planalife;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.planalife.ui.CalendarAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding binding;
    private TextView titleText;
    private Button prevButton, nextButton;

    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });


        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                System.out.println("banco");
                Toast.makeText(MainActivity.this, "nav click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_home:
                System.out.println("bocan");
                Toast.makeText(MainActivity.this, "home click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_notifications:
                System.out.println("boom");
                Toast.makeText(MainActivity.this, "notif click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}