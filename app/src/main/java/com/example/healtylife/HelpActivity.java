package com.example.healtylife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class HelpActivity extends AppCompatActivity {
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Help");
        alertDialog.setMessage("1.Uygulama  içerisinde güncel kilo ve boy değerlerini girerek ideal kilo değerinizi ve Vücut kitle indeksine göre durumunuzu ögrenebilirisiniz.\n" +
                "2.Yiyecek/içecekler menusunden ideal kilo ve vücüt kitle indeksinize göre Yiyeceklerin kalorisine bakıp Size uygun Yemekleri seçebilirisiniz \n"+"" +
                "3. Gps Üzerinde Size Google Map'e Baglanıp Size En yakın Restaurant Kafetaryaları Görebilirsiniz \n"+"" +
                "\n****Sağlıklı Günler dileriz****");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.ic_help);
        alertDialog.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent=new Intent(HelpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }).show();
        alertDialog.setNegativeButton("Bize oy Ver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri.parse("https://play.google.com/store");
                Intent intentimiz =new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store"));
                startActivity(intentimiz);
            }
        }).show();
    }
}