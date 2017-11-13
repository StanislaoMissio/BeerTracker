package com.android.beertracker.integrator;

import com.android.beertracker.entity.Harmonia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HarmoniaAPI {

    @GET("/harmonia/")
    Call<Harmonia> getHarmonia(@Query("id") int id);
}
