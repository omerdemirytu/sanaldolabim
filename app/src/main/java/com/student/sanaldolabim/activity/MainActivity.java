package com.student.sanaldolabim.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.student.sanaldolabim.R;
import com.student.sanaldolabim.fragment.BaseFragment;
import com.student.sanaldolabim.fragment.menu.ActivityFragment;
import com.student.sanaldolabim.fragment.menu.CabinFragment;
import com.student.sanaldolabim.fragment.menu.ClothesFragment;
import com.student.sanaldolabim.fragment.menu.DrawerFragment;

public class MainActivity extends BaseActivity {

    private BaseFragment selectedFragment;
    private ActivityFragment activityFragment;
    private CabinFragment cabinFragment;
    private ClothesFragment clothesFragment;
    private DrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void init() {
        activityFragment = new ActivityFragment();
        cabinFragment = new CabinFragment();
        clothesFragment = new ClothesFragment();
        drawerFragment = new DrawerFragment();

        selectedFragment = drawerFragment;

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        loadFragment(R.id.flFragment, selectedFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.menu_drawer:
                    selectedFragment = drawerFragment;
                    break;
                case R.id.menu_clothes:
                    selectedFragment = clothesFragment;
                    break;
                case R.id.menu_cabin:
                    selectedFragment = cabinFragment;
                    break;
                case R.id.menu_activity:
                    selectedFragment = activityFragment;
                    break;
            }
            loadFragment(R.id.flFragment, selectedFragment);
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (selectedFragment != null) {
            selectedFragment.update();
        }
    }
}