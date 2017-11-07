package com.android.beertracker.service;

import com.android.beertracker.model.Harmonia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HarmoniaService {

    @GET("/harmonia/")
    Call<Harmonia> getHarmonia(@Query("id") int id);
}
