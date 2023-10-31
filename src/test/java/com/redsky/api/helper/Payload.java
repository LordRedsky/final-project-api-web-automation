package com.redsky.api.helper;

import com.redsky.api.utilities.RequestBody;
import org.json.JSONObject;

public class Payload {
    public static JSONObject getRequiredFieldBody() {
        return RequestBody.setupRequiredBody(
                "amin24",
                "tamvan",
                "334356@konoha.com"
        );
    }


}
