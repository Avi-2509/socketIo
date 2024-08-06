package com.personal.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.converter.json.GsonBuilderUtils;

public class AppUtil {

    static Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
    public static String toJson(final Object object){
        return gson.toJson(object);
    }
}
