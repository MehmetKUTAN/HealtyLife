package com.example.healtylife;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {

    HashMap<String,String> parseJsnon (JSONObject jsonObject) throws JSONException {
        HashMap<String,String> hashMap= new HashMap<>();
        String name = jsonObject.getString("name");
        String latiude = jsonObject.getJSONObject("geometry")
                .getJSONObject("locaion").getString("lat");

        String longitude = jsonObject.getJSONObject("geometry")
                .getJSONObject("locaion").getString("lng");
        hashMap.put("name",name);
        hashMap.put("lat",latiude);
        hashMap.put("lng",longitude);


        return hashMap;

    }

    private List<HashMap<String,String>> hashMapList(JSONArray jsonArray) throws JSONException {
        List<HashMap<String,String>> datalist = new ArrayList<>();
        for (int  counter=0; counter<jsonArray.length();counter++)
        {
            HashMap<String,String> data = parseJsnon((JSONObject) jsonArray.get(counter));
            datalist.add(data);
        }

        return  datalist;
    }


    public  List<HashMap<String,String>> parseResult(JSONObject object) throws JSONException {
        JSONArray jsonArray = object.getJSONArray("results");

        return hashMapList(jsonArray) ;

    }
}
