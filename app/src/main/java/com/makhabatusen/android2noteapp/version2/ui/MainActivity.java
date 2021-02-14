package com.makhabatusen.android2noteapp.version2.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.makhabatusen.android2noteapp.R;
import com.makhabatusen.android2noteapp.version2.App;
import com.makhabatusen.android2noteapp.version2.utils.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private BottomNavigationView navView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        initNavController();
        if (!App.getPrefs().isShown())
        navController.navigate(R.id.boardFragment);
    }


    private void initNavController() {

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile
        ).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        hideTabsAndActionBar();

    }

    private void hideTabsAndActionBar() {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                if (destination.getId() == R.id.navigation_home
//                        || destination.getId() == R.id.navigation_dashboard
//                        || destination.getId() == R.id.navigation_notifications
//                        || destination.getId() == R.id.navigation_profile)
//                    navView.setVisibility(View.VISIBLE);
//                else navView.setVisibility(View.GONE);

                // also can create a list
                List<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
                list.add(R.id.navigation_profile);

                if (list.contains(destination.getId()))
                    navView.setVisibility(View.VISIBLE);
                else navView.setVisibility(View.GONE);


                if (destination.getId() == R.id.boardFragment)
                    Objects.requireNonNull(getSupportActionBar()).hide();
                else Objects.requireNonNull(getSupportActionBar()).show();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}