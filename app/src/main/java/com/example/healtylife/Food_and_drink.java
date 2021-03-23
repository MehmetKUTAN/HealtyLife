package com.example.healtylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Food_and_drink extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ExpandableListView yemekler;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    long backpressTime;
    Toast backToast;

    @Override
    public void onBackPressed() {

        if(backpressTime+200>System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast=  Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_drink);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        yemekler=findViewById(R.id.Yemekler);
        yemekler.setAdapter(new islemler(this));


        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;


        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragament_conteinmain,new FragmentMain());
        fragmentTransaction.commit();

        yemekler.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent= new Intent(getApplicationContext(),Bilgi.class);
                String name =((TextView) v).getText().toString();
                intent.putExtra("yemek",name);
                startActivity(intent);
                return false;

            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.MainPage) {
            Intent intent = new Intent(Food_and_drink.this, HomeActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.user_information_button) {
            Intent intent = new Intent(Food_and_drink.this, UserinformationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.human_height_and_weight) {
            Intent intent = new Intent(Food_and_drink.this, human_height_and_weight.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Food_and_Drink) {
            Intent intent = new Intent(Food_and_drink.this, Food_and_drink.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Gps) {
            Intent intent = new Intent(Food_and_drink.this, find_Restaurant.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.Help) {
            Intent intent = new Intent(Food_and_drink.this, HelpActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.log_out) {
            logout();
        }

        return true;
    }

    private void logout() {
        Intent intent= new Intent(Food_and_drink.this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}