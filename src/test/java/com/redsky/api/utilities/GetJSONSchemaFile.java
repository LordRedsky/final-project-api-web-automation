package com.redsky.api.utilities;

import java.io.File;

public class GetJSONSchemaFile {
    static String path = "src/test/resources/api/JSONSchemas/";
    public static File getJSONSchema(String JSONFile){
        return new File(path + JSONFile);
    }
}
