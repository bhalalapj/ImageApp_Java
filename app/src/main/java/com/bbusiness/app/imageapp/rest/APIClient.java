package com.bbusiness.app.imageapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static String _accessKey;
    private static final String BASE_URL = "https://api.unsplash.com/search/";
    private static Retrofit _retroClient = null;

    private static void setAccessKey(String accessKey) {
        if (!accessKey.isEmpty())
            _accessKey = accessKey;
    }

    public static String getAccessKey() {
        return _accessKey;
    }

    public static Retrofit getRetrofitClient() {
        if (_retroClient == null)
            _retroClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        setAccessKey("608ad97262cae0dc5394a41ee8422787d170a84a31984d13d41c34172695aebb");
        return _retroClient;

    }

}
