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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Bilgi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
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
        setContentView(R.layout.activity_bilgi);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView baslik=findViewById(R.id.BilgiTitleText);
        TextView detay=findViewById(R.id.GenelBilgiText);
        Intent intent = getIntent();
        String Bilgi= intent.getStringExtra("yemek");
        baslik.setText(Bilgi);
        System.out.println(intent+" bilgi");
        if(Bilgi.intern()=="Sucuklu Paçanga Böreği")
        {

            detay.setText("Paçanga Böreği Kalori Değeri: Bu besinin 100 gramında 371 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 136 gramlık miktarında Paçanga Böreği 504 kalori bulunmaktadır.");
        }
        if(Bilgi.intern()=="Kremalı Kabak Graten")
        {

            detay.setText("Kabak Graten, sebzeden gelen potasyum, C vitamini ve süt ile kaşar peynirden gelen protein içeriğine sahiptir. Ana öğünde etli sebze yemeği yerine tercih edilebilir.");
        }
        if(Bilgi.intern()=="Kahvaltılık Tereyağlı Patates Kavurması")
        {

            detay.setText("1 adet orta boy (213 gr) patates besin değeri, 37.25 gr karbonhidrat, 4.37 gr protein, 0.19 gr yağ, 4.47 gr lif, 12.78 mg sodyum, 905.25 mg potasyum, 25.56 mg kalsiyum içerir. Çiğ patates kolesterol içermez.");
        }
        if(Bilgi.intern()=="Yoğurtlu Kıymalı Patates Mantısı")
        {

            detay.setText("Yoğurtlu Patates, A, C vitaminleri, folik asit, potasyum, fosfor, magnezyum ve lif açısından zengindir. İçerisindeki yağ miktarına dikkat edilerek ana öğünlerinizin yanında salata olarak tüketebilirsiniz..");
        }
        if(Bilgi.intern()=="Peynirli Mantar Kızartması")
        {

            detay.setText("Mantar Kızartması\n" +
                    "(180 gr)\n" +
                    "226\n" +
                    "kcal\n" +
                    "%33 Karbonhidrat\n" +
                    "%12 Protein\n" +
                    "%56 Yağ \n Mantar Kızartması, yağ içeriği yüksek bir yemek olduğu için çok sık ve çok fazla tüketilmemelidir. Tüketileceği zaman yarım porsiyon kadar tüketilmesi yeterlidir. ");
        }
        if(Bilgi.intern()=="Mantar Sote")
        {

            detay.setText("Mantar Sote Kalori Değeri: Bu besinin 100 gramında 63 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 300 gramlık miktarında Mantar Sote 189 kalori bulunmaktadır..");
        }
        if(Bilgi.intern()=="Kabak Sinkonta")
        {

            detay.setText("Balkabağı (Pişmiş) Kalori Değeri: Bu besinin 100 gramında 20 kcal kalori, ayrıca besinin 1 Su Bardağı yani 245 gramlık miktarında Balkabağı (Pişmiş) 49 kalori bulunmaktadır. Balkabağı, A vitamini ve lif açısından oldukça zengin bir sebzedir."); }

        if(Bilgi.intern()=="Yeşil Mercimekli Gün Salatası")
        {

            detay.setText("Yeşil Mercimek Salatası (100 g) içinde 108 kadar kalori mevcut."); }

        if(Bilgi.intern()=="Domates Çorbası")
        {

            detay.setText("Domates Çorbası Kalori Değeri: Bu besinin 100 gramında 32 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 210 gramlık miktarında Domates Çorbası 68 kalori bulunmaktadır. Domates çorbası, C vitamini, folik asit, potasyum açısından iyi bir kaynaktır.");}
        if(Bilgi.intern()=="Ezo Gelin Çorbası")
        {

            detay.setText("1 kase ezogelin çorbası kaç kalori: 91 kalori. ... 100 gram kasede ezogelin çorbası: 45 kalori. Büyük boy kase ezogelin çorbası: 136 kalori.");}

        if(Bilgi.intern()=="Yuvalama Çorba")
        {

            detay.setText("Yuvalama Çorbası\n" +
                    "nohut 360 kalori, yaklaşık 20 gr bitkisel protein, 5 gr. yağ ve 61 gr. karbonhidrat içerir.");}

        if(Bilgi.intern()=="Mercimek Çorbası")
        {

            detay.setText("Mercimek Çorbası Kalori Değeri: Bu besinin 100 gramında 46 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 300 gramlık miktarında Mercimek Çorbası 138 kalori bulunmaktadır. Mercimek çorbası protein oranı yüksek ve besleyici bir çorbadır. Diyabetikler için ve vejeteryanlar için çok uygun bir öğündür.");  }

        if(Bilgi.intern()=="Fırında Tavuk")
        {

            detay.setText("Fırında Baharatlı Tavuk Kalori Değeri: Bu besinin 100 gramında 211 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 220 gramlık miktarında Fırında Baharatlı Tavuk 465 kalori bulunmaktadır.");}

        if(Bilgi.intern()=="Macar Kebabı")
        {

            detay.setText(" Porsiyon Macar Gulaş 385 kcal'dir (kalori). Orta kalorili ürünler arasında bulunan 385 kalorili 1 Porsiyon Macar Gulaş gıdasını diyet yaparken az miktarda tüketebilirsiniz. 385 kalori orta kalori olduğu için kilo vermek isteyenlere 1 Porsiyon Macar Gulaş gıdası az miktarda tavsiye edilmektedir.Porsiyon olarak diyet yapanlara 385 kalorili 1 Porsiyon Macar Gulaş tavsiye edilmez. Eğer ki tükettiyseniz spor yapmanızı kesinlikle öneririm.");  }
        if(Bilgi.intern()=="Lahmacun")
        {

            detay.setText("İnce hamurlu lahmacunlar yaklaşık olarak 180 ile 200 kalori arasındadır. Kalın hamurlu lahmacunlar ise en fazla 240 kaloridir.");  }
        if(Bilgi.intern()=="Meftune")
        {

            detay.setText("Etli Türlü (Meftune)\n" +
                    "(296.9 gr) 221kcal. \n %28 Karbonhidrat\n" +
                    "%27 Protein\n" +
                    "%45 Yağ. \n  Etli türlü, taze fasülye, kabak, patlıcan, patates gibi sebzelerle yapılan vitamin, mineral ve lif içeriği yüksek bir yemektir. Etli olması yemeğin protein, vitamin, mineral değerini artırmıştır. Etli türlü hem öğle hem de akşam yemeğinde tüketebilir");
        }
        if(Bilgi.intern()=="Kolay Un helvası")
        {

            detay.setText("Dondurmalısı, cevizlisi, irmiklisi farklı farklı çeşitlerine hâlihazırda alışkın olduğumuz helvanın en bilineni kuşkusuz un helvası. Yağda ağır ağır kavrulmuş unun şerbetle buluşmasıyla lezzetini katlayan un helvası kalorisi maalesef hem undan hem yağdan hem de şekerden bolca zengin. Bu nedenle kilo kontrolü sürecinde olmasanız bile ölçülü tüketmeniz gereken tatlar arasında.\n" +
                    "\n" +
                    "Peki, un helvası kaç kalori?\n" +
                    "\n" +
                    "1 yemek kaşığı (20 gr ) un helvası: 65 kalori\n" +
                    "1 porsiyon (90 gr) un helvası: 294 kalori\n" +
                    "100 gram (5 yemek kaşığı) un helvası: 327 kalori");}
        if(Bilgi.intern()=="Karamel Yapımı")
        {

            detay.setText("Krem Karamel\n" +
                    "(190 gr) 297 kcal\n Krem karamel, süt, yumurta, şekerden yapılan bir tatlıdır. Şeker içeriği oldukça yüksek olduğundan diyabetliler ve kilo kontrolü sağlamak isteyenler tarafından tüketilmemelidir.");}
        if(Bilgi.intern()=="Şekerpare")
        {

            detay.setText("1 şekerpare kaç kalori? 1 adet şekerpare: 113 kcal. 1 orta porsiyon (150 gr) şekerpare: 424 kcal. 100 gram şekerpare: 283 kcal.");}
        if(Bilgi.intern()=="Fırında Sütlaç")
        {

            detay.setText("Fırın Sütlaç, şeker ve pirinç içeriğine sahip olduğu için özellikle şeker hastalarının tüketmemeye özen göstermesi gerekir. Tüketileceği zaman, yarım kase kadar tüketilmesi yeterlidir.");}
        if(Bilgi.intern()=="Ülker Çikolatalı Gofret")
        {

            detay.setText("Çikolatalı Gofret Kalori Değeri: Bu besinin 100 gramında 565 kcal kalori, ayrıca besinin 1 Porsiyon (Orta) yani 35 gramlık miktarında Çikolatalı Gofret 198 kalori bulunmaktadır. Çikolatalı gofret, yüksek yağ ve karbonhidrat içeriğine sahiptir.");}
        if(Bilgi.intern()=="Ülker Fındıklı Çikolata")
        {

            detay.setText("100 g içinde 585 kadar kalori mevcut."); }
        if(Bilgi.intern()=="Ülker Halley")
        {

            detay.setText("1 adet Halley yaklaşık 132 kaloridir.\n" +
                    "Halley'i zaman zaman yemek kilo aldırmaz fakat gün içinde aşırı miktarda tüketilmesi, vücutta yağ ve şeker birikimine yol açar."); }
        if(Bilgi.intern()=="Ülker Napoliten")
        {

            detay.setText("Ülker Napoliten ürünündeki her bir çikolata yaklaşık 4,12 gramdır. Tek bir çikolatada yaklaşık olarak 58 kcal enerji bulunmaktadır. Ve her bir çikolata yaklaşık 1 adet küp şeker kadar ilave şeker içermektedir.");  }
        if(Bilgi.intern()=="Vegan Brownie")
        {

            detay.setText("Brownie seviyorsanız, ama kalorili olduğu için yemek istemiyorsanız, ya da vegan olduğunuz için yiyemiyorsanız bu tarifimiz sizin için. Fırında pişirmenize gerek yok, şekersiz, glütensiz ve sütsüz yapılan bu browninin ısırığı 59 kalori… Her ısırıkta iki gram protein ve lif sunmaktadır.");}
        if(Bilgi.intern()=="Girit Kabağı Dolması")
        {

            detay.setText("00 gr için belirlenen besin değerleridir.\n" +
                    "Enerji/kkal\t70 kkal\n" +
                    "Protein\t3,3 gr\n" +
                    "Karbonhidrat\t10,5 gr\n" +
                    "Yağ\t1,7 gr"); }
        if(Bilgi.intern()=="Sebzeli Güveç")
        {

            detay.setText("Sebzeli kebap, sebze içeriği yüksek olduğu için vitamin ve mineral açısından oldukça zengindir. Öğünde et ve sebze hakkı yerine 1 orta porsiyon tüketilebilir.");}
        if(Bilgi.intern()=="Yumurtasız Mücver")
        {

            detay.setText("Mücver, un, kabak, yumurta ve çeşitli baharatlar kullanılarak yapılan bir yemektir. Kızartma işlemi uygulanır bu nedenle tüketimi sakıncalıdır. Tüketileceği zaman, 3 adet kadar tüketilmesi yeterlidir.");
        }
        if(Bilgi.intern()=="Kola")
        {

            detay.setText(" yemeklerin yanında olmazsa olmazlarımızdan biri olan kolanın kaç kalori olduğu hakkında bilginiz var mı? Bahsedeceğim sadece 1 bardak kolanın vücudumuza nedenli etkili olduğunu bir görelim.\n" +
                    "\n" +
                    "1 bardak kola: 89 Kalori içeriyor.\n" +
                    "\n" +
                    "1 kutu kola: 110 Kalori – 10 küp şeker değerinde enerji alınmasını sağlamaktadır.\n" +
                    "\n" +
                    "1 litre kola: 412 Kalori içeriyor 16,5 küp şekere denk geliyor.\n" +
                    "\n" +
                    "Kolanın besin değeri:\n" +
                    "\n" +
                    "330 ml. 1 kola, 37 gr. karbonhidrat içerir.\n" +
                    "\n" +
                    "300 ml. 1 kola, 0 protein içerir.\n" +
                    "\n" +
                    "300 ml. kola, 0 yağ içerir.");
        }
        if(Bilgi.intern()=="Su")
        {

            detay.setText("Su Kalori Değeri: Bu besinin 100 gramında 0 kcal kalori, ayrıca besinin 1 Su Bardağı (200 ml lik) yani 200 gramlık miktarında Su 0 kalori bulunmaktadır.");
        }
        if(Bilgi.intern()=="Ayran")
        {

            detay.setText("1 bardak tam yağlı yoğurt kullanılarak yapılan ayran 150 mililitredir ve yaklaşık 60 kaloridir. Yarım yağlı ayran yaklaşık 40 kalori, Yağsız ayran yaklaşık 15-25 kaloridir. 1 kutu ayran yaklaşık 200 mililitredir ve yaklaşık 80 kaloridir.");
        }
        if(Bilgi.intern()=="Çay")
        {

            detay.setText("çayın yüksek miktarda tein içeriyor olması diyetisyen listesi içerisinde yer verilip, verilmemesi tartışmasının daha fazla ön planda olmasındaki en önemli etkenlerden birisini oluşturmaktadır. Bu noktada şekersiz çay önerisi diyet programlarına yönelik öne çıkarken, 1 bardak şekersiz çay 0 kcal kalori içermektedir.");
        }



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


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.MainPage) {
            Intent intent = new Intent(Bilgi.this, HomeActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.user_information_button) {
            Intent intent = new Intent(Bilgi.this, UserinformationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.human_height_and_weight) {
            Intent intent = new Intent(Bilgi.this, human_height_and_weight.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Food_and_Drink) {
            Intent intent = new Intent(Bilgi.this, Food_and_drink.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Gps) {
            Intent intent = new Intent(Bilgi.this, find_Restaurant.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.Help) {
            Intent intent = new Intent(Bilgi.this, HelpActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.log_out) {

        }

        return true;
    }
}