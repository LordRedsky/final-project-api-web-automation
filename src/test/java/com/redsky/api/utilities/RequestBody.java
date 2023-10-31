package com.redsky.api.utilities;

import org.json.JSONObject;

public class RequestBody {
    public static JSONObject setupRequiredBody(String firstName, String lastName, String email) {
        JSONObject body = new JSONObject();

        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);

        return body;
    }

    public static JSONObject setupFullBody(String title, String firstName, String lastName, String picture, String gender, String email, String dateOfBirth, String phone, String country, String city, String street, String timezone, String state) {
        JSONObject location = new JSONObject();
        location.put("country", country);
        location.put("city", city);
        location.put("street", street);
        location.put("timezone", timezone);
        location.put("state", state);

        JSONObject body = new JSONObject();
        body.put("title", title);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("picture", picture);
        body.put("gender", gender);
        body.put("email", email);
        body.put("dateOfBirth", dateOfBirth);
        body.put("phone", phone);
        body.put("location", location);

        return body;
    }
}
