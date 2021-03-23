package com.example.healtylife;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class islemler extends BaseExpandableListAdapter {

    String[] liste={"Sicak Baslangic","Ara soğuklar","Çorbalar","Ana yemekler","Tatlılar","Atıştırmalık(Snack Food)","Vegan Yemekleri","İçeçekler"};
    String[][] listeicerk={{"Sucuklu Paçanga Böreği","Kremalı Kabak Graten","Kahvaltılık Tereyağlı Patates Kavurması","Yoğurtlu Kıymalı Patates Mantısı"},
            {"Peynirli Mantar Kızartması","Mantar Sote","Kabak Sinkonta","Yeşil Mercimekli Gün Salatası"},
            {"Domates Çorbası","Ezo Gelin Çorbası","Yuvalama Çorba","Mercimek Çorbası"},
            {"Fırında Tavuk","Macar Kebabı","Meftune","Lahmacun"},
            {"Kolay Un helvası","Karamel Yapımı","Şekerpare","Fırında Sütlaç"},
            {"Ülker Çikolatalı Gofret","Ülker Fındıklı Çikolata","Ülker Halley","Ülker Napoliten"},
            {"Vegan Brownie","Girit Kabağı Dolması"," Sebzeli Güveç","Yumurtasız Mücver"},
            {"Kola","Su","Ayran","Çay"}};


    Context context;
   public islemler(Context context)
    {
        this.context=context;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
     @Override
    public int getGroupCount() {
        return liste.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listeicerk[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(liste[groupPosition]);
        textView.setTextSize(24);
        textView.setPadding(40,10,10,10);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       TextView textView2= new TextView(context);
        textView2.setText(listeicerk[groupPosition][childPosition]);
        textView2.setTextSize(16);
        textView2.setPadding(40,10,10,10);
        return textView2;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
