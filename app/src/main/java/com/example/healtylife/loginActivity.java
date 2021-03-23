package com.example.healtylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.SharedPreferences.*;

public class loginActivity extends AppCompatActivity {
    EditText Username,password;
    Button Singin_button,SignUp;
    CheckBox remember;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username=findViewById(R.id.Username);
        password=findViewById(R.id.password);
        Singin_button=findViewById(R.id.Singin_button);
        remember=findViewById(R.id.remember);
        SignUp=findViewById(R.id.SignUp);
        preferences=getSharedPreferences("Userinfo", 0);



        getPreferencesDate();
        Singin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue=Username.getText().toString();
                String PasswordValue=password.getText().toString();
                String RegisteredUserName=preferences.getString("username","");
                String RegisteredPassword=preferences.getString("password","");
                if(usernameValue.equals(RegisteredUserName) && PasswordValue.equals(RegisteredPassword)){
                    Toast.makeText(loginActivity.this, "Login Succesfull!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(loginActivity.this, HomeActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(loginActivity.this, "UserName or Password Wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(remember.isChecked())
                {
                    Boolean boolschecked=remember.isChecked();
                    Editor editor=preferences.edit();
                    editor.putString("Username",Username.getText().toString());
                    editor.putBoolean("userchacked",boolschecked);
                    editor.apply();
                }
                else
                {
                    if (Username==null) {
                        preferences.edit().clear().apply();
                        Toast.makeText(loginActivity.this, "Please Sign in", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void getPreferencesDate() {
        if(preferences.contains("Username"))
        {
            String name=preferences.getString("Username","not found.");
            Username.setText(name.toString());
        }
        if (preferences.contains("userchacked"))
        {
            Boolean checkedd=preferences.getBoolean("userchacked",false);
            remember.setChecked(checkedd);
        }
    }
}