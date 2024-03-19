package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONObject;

public class Jsonpractice2 {
    public static void main(String[] args) {
        try {
            String jsonString = "{\"name\":\"NHN Academy\"}";
            JSONObject object = new JSONObject(jsonString);

            object.put("name", "Rename");

            String addressStr = "{\"code\":13487, \"city\":\"Seongnam\"}";
            JSONObject address = new JSONObject(addressStr);
            address.put("code", "1244");
            address.put("city", "gwangju");
            object.put("address", address);
            System.out.println(object);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }

    }
}
