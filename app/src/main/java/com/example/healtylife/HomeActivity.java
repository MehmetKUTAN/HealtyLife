package com.example.healtylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name_Display,surname_Display,Email_Display;
    SharedPreferences preferences;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    long backpressTime;
    Toast backToast;

    @Override
    public void onBackPressed() {

        if(backpressTime+2000>System.currentTimeMillis()) {
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
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences("Userinfo", 0);
        name_Display = findViewById(R.id.nameDisplayHome);
        surname_Display=findViewById(R.id.SurnameDisplayHome);
      //  Email_Display=findViewById(R.id.email_display_drawer);
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




        String name = preferences.getString("Name", null);
        String surname = preferences.getString("Surname", null);
        String username = preferences.getString("username", null);
       // String email = preferences.getString("Email", null);
        if (username != null) {
            name_Display.setText(" " + name);
            surname_Display.setText(" " + surname);
            //Email_Display.setText(" "+email);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.MainPage)
        {
            Intent intent=new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.user_information_button)
        {
            Intent intent=new Intent(HomeActivity.this, UserinformationActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.human_height_and_weight)
        {
            Intent intent=new Intent(HomeActivity.this, human_height_and_weight.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.Food_and_Drink)
        {
            Intent intent=new Intent(HomeActivity.this, Food_and_drink.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.Gps)
        {
            Intent intent=new Intent(HomeActivity.this, find_Restaurant.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.Help)
        {
            Intent intent=new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.log_out)
        {
            logout();
        }

        return true;
    }

    private void logout() {
        Intent intent= new Intent(HomeActivity.this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}