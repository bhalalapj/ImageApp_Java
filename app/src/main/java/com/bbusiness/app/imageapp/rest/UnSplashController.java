package com.bbusiness.app.imageapp.rest;

import android.util.Log;

import com.bbusiness.app.imageapp.model.UnSplashResponse;
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UnSplashController extends RestBaseController {
    private static UnSplashRepository _photoRepo;

    private static UnSplashController _instance;

    public static UnSplashController getInstance() {
        if (_instance == null)
            _instance = new UnSplashController();
        return _instance;
    }


    @Override
    public List<UnsplashPhoto> getPhotos(String type) {
        final Object[] resp = {null};
        try {
            setAccessKey("608ad97262cae0dc5394a41ee8422787d170a84a31984d13d41c34172695aebb");
            GetPhotoService serv = getRetrofitClient().create(GetPhotoService.class);
            serv.GetPhotos(type, getAccessKey()).enqueue(new Callback<UnSplashResponse>() {
                @Override
                public void onResponse(Call<UnSplashResponse> call, Response<UnSplashResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("API", "Response " + response.message());
                        resp[0] = response.body().toString();
                    } else {
                        Log.e(TAG, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("API", t.getMessage());
                }
            });

        } catch (Exception ex) {
            Log.e(TAG, "getPhotos: " + ex.getMessage());
        }
        return (List<UnsplashPhoto>) resp[0];
    }
}
