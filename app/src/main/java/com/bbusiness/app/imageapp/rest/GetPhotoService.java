package com.bbusiness.app.imageapp.rest;

import com.bbusiness.app.imageapp.model.UnSplashResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPhotoService {
    @GET("photos")
    Call<UnSplashResponse> GetPhotos(@Query("query") String type, @Query("client_id") String apiKey);
}
