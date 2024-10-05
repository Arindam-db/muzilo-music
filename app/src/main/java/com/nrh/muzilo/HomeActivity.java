package com.nrh.muzilo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homeActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup bottom navigation and fragments
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                loadFragment(new HomeFragment(), false);
            } else if (itemId == R.id.navigation_playlists) {
                loadFragment(new LibraryFragment(), false);
            } else { // nav profile
                loadFragment(new EqualizerFragment(), false);
            }

            return true;});

        // Load initial fragment (Home) after setting the listener
        loadFragment(new HomeFragment(), true);
    }

    // Method to load fragments into the FrameLayout
    private void loadFragment(Fragment fragment, boolean isAppInitialised) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialised) {
            fragmentTransaction.add(R.id.frame_layout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();
    }
}