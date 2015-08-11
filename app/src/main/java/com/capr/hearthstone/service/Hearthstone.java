package com.capr.hearthstone.service;

import com.capr.hearthstone.beans.Info;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by CRISTIAN on 10/08/2015.
 */
public interface Hearthstone {

    @Headers("X-Mashape-Key: BRGxteEFzFmshQItCIFu8g5ntVrKp15JpkFjsnr8kbh1losdFD")
    @GET("/info")
    Info getSInfo();

    @Headers("X-Mashape-Key: BRGxteEFzFmshQItCIFu8g5ntVrKp15JpkFjsnr8kbh1losdFD")
    @GET("/info")
    void getAInfo(Callback<Info> cb);
}
