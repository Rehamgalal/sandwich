package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwitch = new JSONObject(json);
            JSONObject name= sandwitch.getJSONObject("name");
            String mainname = name.getString("mainName");
            ArrayList<String> AlsoknownAs= new ArrayList<String>();
            JSONArray alsoknown=name.getJSONArray("alsoKnownAs");
            if(alsoknown!=null){
                for (int i = 0 ; i<alsoknown.length();i++)
                    AlsoknownAs.add(alsoknown.getString(i));
            }
            String placeoforigin = sandwitch.getString("placeOfOrigin");
            String description = sandwitch.getString("description");
            String image = sandwitch.getString("image");
            ArrayList<String> Ingredients = new ArrayList<String>();
            JSONArray ingridients = sandwitch.getJSONArray("ingredients");
            if(ingridients!=null){
                for(int i=0 ; i<ingridients.length(); i++)
                    Ingredients.add(ingridients.getString(i));
            }
            Sandwich S= new Sandwich(mainname,AlsoknownAs,placeoforigin,description,image,Ingredients);
            return S;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
