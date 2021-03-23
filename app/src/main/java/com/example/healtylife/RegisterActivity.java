package com.example.healtylife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG="RegisterActivity" ;

    EditText Username,Email,password,phone,Name,Surname;
    TextView date;
    RadioGroup gender;
    Button Register,cancel_button;
    SharedPreferences preferences;
    ImageView btnCalendar;
    DatePickerDialog datePickerDialog;

    int year ;
    int month;
    int dayOfMonth;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name=findViewById(R.id.name);
        Surname=findViewById(R.id.Surname);
        Username=findViewById(R.id.Username);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        date=findViewById(R.id.date);
        btnCalendar=findViewById(R.id.Calender);
        phone=findViewById(R.id.phone);
        gender=findViewById(R.id.gender);
        Register=findViewById(R.id.Register);
        cancel_button=findViewById(R.id.cancel);

        preferences=getSharedPreferences("Userinfo", 0);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameValue = Name.getText().toString();
                String SurNameValue = Surname.getText().toString();
                String usernameValue = Username.getText().toString();
                String EmailValue = Email.getText().toString();
                String passwordValue = password.getText().toString();
                String dateValue = date.getText().toString();
                String phoneValue = phone.getText().toString();
                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkedBtn.getText().toString();

                if(usernameValue.length()>1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name", NameValue);
                    editor.putString("Surname", SurNameValue);
                    editor.putString("username", usernameValue);
                    editor.putString("Email", EmailValue);
                    editor.putString("password", passwordValue);
                    editor.putString("date", dateValue);
                    editor.putString("phone", phoneValue);
                    editor.putString("gender", genderValue);
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "User Registered!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this, loginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter a Value !", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                date.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },year,month,dayOfMonth);
                datePickerDialog.show();
            }

        });



        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyfield();
                Intent intent=new Intent(RegisterActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void emptyfield()
    {
        Name.setText("");
        Surname.setText("");
        Username.setText("");
        Email.setText("");
        password.setText("");
        date.setText("");
        phone.setText("");

    }
}