package com.bbusiness.app.imageapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RestBaseController implements UnSplashRepository {
    //    @Override
//    public abstract List<UnsplashPhoto> getPhotos(String type);
    private String _accessKey;
    private static final String BASE_URL = "https://api.unsplash.com/search/";
    private static Retrofit _retroClient = null;

    public void setAccessKey(String accessKey) {
        if (!accessKey.isEmpty())
            _accessKey = accessKey;
    }

    public String getAccessKey() {
        return _accessKey;
    }

//    public T getJson(String type) {
//        Retrofit client = getRetrofitClient("?query=" + type);
//
//    }

    public Retrofit getRetrofitClient() {
        if (_retroClient == null)
            _retroClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return _retroClient;

    }

}
