package com.semester_project.smd_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.semester_project.smd_project.R;

import Fragments.CreateTrip_Fragment;
import Fragments.Dashboard_Fragment;
import Fragments.Fragment_Help;
import Fragments.Fragment_Setting;
import Fragments.Profile_Fragment;
import Fragments.TripHistoryFragment;
import Fragments.Wallet_Fragment;

public class MainActivity extends AppCompatActivity
{
    private ImageView bars;
    public DrawerLayout drawyer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Dashboard_Fragment()).commit();
        }

        bars = (ImageView) findViewById(R.id.barsid);
        drawyer = (DrawerLayout) findViewById(R.id.drwr);
        bars.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (drawyer.isDrawerOpen(Gravity.LEFT))
                {
                    bars.setImageDrawable(getResources().getDrawable(R.drawable.drawer_bars));
                    drawyer.closeDrawer(Gravity.LEFT);
                }
                else
                {
                    bars.setImageDrawable(getResources().getDrawable(R.drawable.back));
                    drawyer.openDrawer(Gravity.LEFT);
                }
            }
        });

        NavigationView navi = findViewById(R.id.sidenavigation);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        selectedFragment = new Dashboard_Fragment();
                        break;

                    case R.id.profile:
                        selectedFragment = new Profile_Fragment();
                        break;

                    case R.id.createtrip:
                        selectedFragment = new CreateTrip_Fragment();
                        break;

                    case R.id.wallet:
                        selectedFragment = new Wallet_Fragment();
                        break;
                    case R.id.mytrips:
                        selectedFragment = new TripHistoryFragment();
                        break;
                    case R.id.setting:
                        selectedFragment = new Fragment_Setting();
                        break;
                    case R.id.help:
                        selectedFragment = new Fragment_Help();
                        break;
                    case R.id.logout:
                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        finish();
                        startActivity(intent);
                        break;
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                bars.setImageDrawable(getResources().getDrawable(R.drawable.drawer_bars));
                drawyer.closeDrawer(Gravity.LEFT);

                return true;
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drwr);
        if (drawer.isDrawerOpen(Gravity.LEFT))
        {
            drawer.closeDrawer(Gravity.LEFT);
        }
        else {
            super.onBackPressed();
        }
    }
}