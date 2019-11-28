package com.bbusiness.app.imageapp.rest;

import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto;

import java.util.List;

public interface UnSplashRepository {

    List<UnsplashPhoto> getPhotos(String type);

}
