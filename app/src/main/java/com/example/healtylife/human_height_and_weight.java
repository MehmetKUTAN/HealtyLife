package com.example.healtylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class human_height_and_weight extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText editText;
    TextView boy_Degeri, Kilo_Degeri, resulttextt, statustext;
    SeekBar BoyseekBar, kiloseekBar;
    RadioGroup gendergroup;
    boolean erkekmi = true;
    double boy = 0.00;
    int kilo = 20;
    SharedPreferences preferences;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    long backpressTime;
    Toast backToast;
    Context context=this;

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


    ////////////////////////////
    SeekBar.OnSeekBarChangeListener boySeekbarisleyicisi = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            try {
                boy = Double.parseDouble(String.valueOf(progress)) / 10.0;
            } catch (NumberFormatException e) {
            }
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener kiloSeekbarisleyicisi = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo = progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    RadioGroup.OnCheckedChangeListener genderradiogroupisleyicisi = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.MaleRadio) {
                erkekmi = true;
            }
            if (checkedId == R.id.FemaleRadio) {
                erkekmi = false;
            }
            guncelle();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_height_and_weight);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences("Userinfo", 0);
        boy_Degeri = findViewById(R.id.boy_degeri);
        Kilo_Degeri = findViewById(R.id.Kilo_degeri);
        resulttextt = findViewById(R.id.ResultText);
        statustext = findViewById(R.id.StatusText);
        BoyseekBar = findViewById(R.id.BoyseekBar);
        kiloseekBar = findViewById(R.id.kiloseekBar);
        gendergroup = findViewById(R.id.gender);

        BoyseekBar.setOnSeekBarChangeListener(boySeekbarisleyicisi);
        kiloseekBar.setOnSeekBarChangeListener(kiloSeekbarisleyicisi);
        gendergroup.setOnCheckedChangeListener(genderradiogroupisleyicisi);
        guncelle();

//Fragment
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragament_conteinmain, new FragmentMain());
        fragmentTransaction.commit();
    }

    private void guncelle() {
        Kilo_Degeri.setText(String.valueOf(kilo) + " gr");
        boy_Degeri.setText(String.valueOf(boy) + " m");
        float ideal_kiloErkek = (float) (50 + 2.3 * ((boy * 100 * 0.4) - 60));
        float ideal_KiloKadın = (float) (45.5 + 2.3 * ((boy * 100 * 0.4) - 60));
        double vki = kilo / (boy * boy);

        if (erkekmi == true) {
            //erkek ise
            resulttextt.setText(String.valueOf(ideal_kiloErkek));
            if (vki <= 20.7) {
                statustext.setText(String.valueOf("Zayif"));
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Durmunuz Zayıf");
                alertDialog.setMessage("Doktora Gitmelisiniz");
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.drawable.ic_help);
                alertDialog.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else if (vki > 20.7 && vki <= 26.4) {
                statustext.setText(String.valueOf("Normal Kilo"));
            } else if (vki > 26.4 && vki <= 27.8) {
                statustext.setText(String.valueOf("Normal Kilodan Fazla"));
            } else if (vki > 27.8 && vki <= 31.1) {
                statustext.setText(String.valueOf("Kilonuz Fazla"));
            } else if (vki > 31.1 && vki <= 34.9) {
                statustext.setText(String.valueOf("Obezsiniz"));
            } else  if (vki > 34.9 ) {
                statustext.setText(String.valueOf("Aşırı kilolu"));
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Durmunuz Aşırı kilolu");
                alertDialog.setMessage("Doktora Gitmelisiniz");
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.drawable.ic_help);
                alertDialog.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
            else
            {
                statustext.setText(String.valueOf(""));
            }
        }

        else {
            //Bayan  için
            resulttextt.setText(String.valueOf(ideal_KiloKadın));
            if (vki <= 19.1) {
                statustext.setText(String.valueOf("Zayif"));
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Durmunuz Zayif");
                alertDialog.setMessage("Doktora Gitmelisiniz");
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.drawable.ic_help);
                alertDialog.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else if (vki > 19.1 && vki <= 25.8) {
                statustext.setText(String.valueOf("Normal Kilo"));
            } else if (vki > 25.8 && vki <= 27.3) {
                statustext.setText(String.valueOf("Normal Kilodan Fazla"));
            } else if (vki > 27.3 && vki <= 32.3) {
                statustext.setText(String.valueOf("Kilonuz Fazla"));
            } else if (vki > 32.3 && vki <= 34.9) {
                statustext.setText(String.valueOf("Obezsiniz"));
            } else if (vki > 34.9){
                statustext.setText(String.valueOf("Aşırı kilolu"));
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Durmunuz Aşırı kilolu");
                alertDialog.setMessage("Doktora Gitmelisiniz");
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.drawable.ic_help);
                alertDialog.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();


            }
            else
            {
                statustext.setText(String.valueOf(""));
            }

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.MainPage) {
            Intent intent = new Intent(human_height_and_weight.this, HomeActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.user_information_button) {
            Intent intent = new Intent(human_height_and_weight.this, UserinformationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.human_height_and_weight) {
            Intent intent = new Intent(human_height_and_weight.this, human_height_and_weight.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Food_and_Drink) {
            Intent intent = new Intent(human_height_and_weight.this, Food_and_drink.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Gps) {
            Intent intent = new Intent(human_height_and_weight.this, find_Restaurant.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.Help) {
            Intent intent = new Intent(human_height_and_weight.this, HelpActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.log_out) {
            logout();
        }

        return true;
    }

    private void logout() {
        Intent intent= new Intent(human_height_and_weight.this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}