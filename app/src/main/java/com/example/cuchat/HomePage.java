package com.example.cuchat;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class HomePage extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);



        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_info));

        bottomNavigation.setOnShowListener(item -> {
            Fragment fragment = null;

            switch (item.getId()) {
                case 1:
                    fragment = new com.example.cuchat.NotificationsFragment();
                    break;
                case 2:
                    fragment = new HomeFragment();
                    break;
                case 3:
                    fragment = new com.example.cuchat.InfoFragment();
                    break;
                default:
                    break;
            }
            loadFragment(fragment);
        });
        //Set notification count
        bottomNavigation.setCount(1, "10");
        //set home fragment initially selected
        bottomNavigation.show(2, true);

        //Set onClick listener
        bottomNavigation.setOnClickMenuListener(item -> Toast.makeText(getApplicationContext()
                , "You Clicked" + item.getId()
                , Toast.LENGTH_SHORT).show());

        bottomNavigation.setOnReselectListener(item -> Toast.makeText(getApplicationContext()
                ,"You re-selected " + item.getId()
                ,Toast.LENGTH_SHORT).show());
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}
