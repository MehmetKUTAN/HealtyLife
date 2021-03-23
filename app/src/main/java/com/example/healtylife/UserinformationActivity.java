package com.example.healtylife;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UserinformationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView Username_Dispay, Email_Dispay, phone_Dispay, Name_Dispay, Surname_Dispay;
    TextView date_Dispay;
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
        setContentView(R.layout.activity_userinformation);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences("Userinfo", 0);
        Name_Dispay = findViewById(R.id.nameDisplay);
        Surname_Dispay = findViewById(R.id.SurnameDisplay);
        Username_Dispay = findViewById(R.id.UsernameDisplay);
        date_Dispay = findViewById(R.id.DateDisplay);
        Email_Dispay = findViewById(R.id.E_mailDisplay);
        phone_Dispay = findViewById(R.id.PhoneDisplay);

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
        String date = preferences.getString("date", null);
        String email = preferences.getString("Email", null);
        String phone = preferences.getString("phone", null);
        if (username != null) {
            Name_Dispay.setText(" " + name);
            Surname_Dispay.setText(" " + surname);
            Username_Dispay.setText(" " + username);
            date_Dispay.setText(" " + date);
            Email_Dispay.setText(" " + email);
            phone_Dispay.setText(" " + phone);
                   }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.MainPage) {
            Intent intent = new Intent(UserinformationActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.user_information_button) {
            Intent intent = new Intent(UserinformationActivity.this, UserinformationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.human_height_and_weight) {
            Intent intent = new Intent(UserinformationActivity.this, human_height_and_weight.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Food_and_Drink) {
            Intent intent = new Intent(UserinformationActivity.this, Food_and_drink.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Gps) {
            Intent intent = new Intent(UserinformationActivity.this, find_Restaurant.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.Help) {
            Intent intent = new Intent(UserinformationActivity.this, HelpActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.log_out) {

            logout();
        }

        return true;
    }

    private void logout() {
        Intent intent= new Intent(UserinformationActivity.this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}