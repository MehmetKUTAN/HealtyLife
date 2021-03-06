package com.example.healtylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class find_Restaurant extends AppCompatActivity {
    Spinner spType;
    Button btnfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentlat=0,currentlog=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__restaurant);

        spType = findViewById(R.id.sp_type);
        btnfind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        String[] placeTypeList = {"restoranlar", "Kafe", "paket servisi"};
        String[] placeNameList = {"Restoranlar", "Kafe", "Paket servisi"};
        spType.setAdapter(new ArrayAdapter<>(find_Restaurant.this
                , R.layout.support_simple_spinner_dropdown_item, placeNameList));


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(find_Restaurant.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else
        {
            ActivityCompat.requestPermissions(find_Restaurant.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= spType.getSelectedItemPosition();
                String url="https://maps.googleapis.com./maps/api/place/nearbysearch/json"+
                        "?location="+currentlat+","+currentlog+"&radius=5000"+"&types="+placeTypeList[count]+
                        "&sensor=true"+"&key"+getResources().getString(R.string.google_map_key);

                    new PlaceTask().execute(url);

            }
        });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                currentlat=location.getLatitude();
                currentlog=location.getLongitude();
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map=googleMap;
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentlat,currentlog),10));


                    }
                });
                }
            }
        });
    }

    public class  PlaceTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
String data =null;
            try {
                 data=downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute();
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url= new URL(string);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream=connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder= new StringBuilder();
        String line ="";
        while ((line=reader.readLine())!= null)
        {
            builder.append(line);
        }

        String data = builder.toString();
        reader.close();
        return  data;

    }

    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser jsonParser= new JsonParser();
            List<HashMap<String,String>> maplist=null;
            JSONObject object=null;
            try {
                object= new JSONObject(strings[0]);
                maplist = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return maplist;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            map.clear();
            for (int i=0;i<hashMaps.size(); i++)
            {
                HashMap<String,String> stringHashMap= hashMaps.get(i);
                double lat=Double.parseDouble(stringHashMap.get("lat"));
                double lng =Double.parseDouble(stringHashMap.get("lng"));
                String name=stringHashMap.get("name");

                LatLng latLng= new LatLng(lat,lng);
                MarkerOptions options= new MarkerOptions();
                options.position(latLng);
                options.title(name);
                map.addMarker(options);
            }
        }
    }
}