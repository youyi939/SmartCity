package com.example.smartcity_test2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartcity_test2.ui.Personal.PersonalFragment;
import com.example.smartcity_test2.ui.dashboard.DashboardFragment;
import com.example.smartcity_test2.ui.home.HomeFragment;
import com.example.smartcity_test2.ui.info.InfoFragment;
import com.example.smartcity_test2.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private final HomeFragment homeFragment = new HomeFragment();
    private final InfoFragment infoFragment = new InfoFragment();
    private final NotificationsFragment notificationsFragment = new NotificationsFragment();
    private final DashboardFragment dashboardFragment = new DashboardFragment();
    private final PersonalFragment personalFragment = new PersonalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        //首页面选择首页
        navView.setSelectedItemId(R.id.navigation_home);
        replaceFragment(homeFragment);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_info:
                        replaceFragment(infoFragment);
                        break;
                    case R.id.navigation_home:
                        replaceFragment(homeFragment);
                        break;
                    case R.id.navigation_notifications:
                        replaceFragment(notificationsFragment);
                        break;
                    case R.id.navigation_dashboard:
                        replaceFragment(dashboardFragment);
                        break;
                    case R.id.navigation_Personal:
                        replaceFragment(personalFragment);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });


    }


    /**
     * 动态切换fragment
     * @param fragment
     */
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}