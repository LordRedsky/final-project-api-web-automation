package com.redsky.api.helper;

import com.redsky.api.utilities.RequestBody;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.Map;

public class Payload {
    public static JSONObject getRequiredFieldBody(Map<String, String> body) {
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String email = body.get("email");

        if (firstName == null || lastName == null) {
            firstName = "";
            lastName = "";
        }

        return RequestBody.setupRequiredBody(
                firstName,
                lastName,
                email
        );
    }

    public static JSONObject getFullFieldBody(Map<String, String> body) {
        String title = body.get("title");
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String picture = body.get("picture");
        String gender = body.get("gender");
        String email = body.get("email");
        String dateOfBirth= body.get("dateOfBirth");
        String phone = body.get("phone");
        String country = body.get("country");
        String city = body.get("city");
        String street = body.get("street");
        String timezone = body.get("timezone");
        String state = body.get("state");

        System.out.println(firstName);
        System.out.println(email);
        System.out.println("masuk disini full");

        return RequestBody.setupFullBody(
                title,
                firstName,
                lastName,
                picture,
                gender,
                email  ,
                dateOfBirth,
                phone,
                country,
                city,
                street,
                timezone,
                state
        );
    }


}
