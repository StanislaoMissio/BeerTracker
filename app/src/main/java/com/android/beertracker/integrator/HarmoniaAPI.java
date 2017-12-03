package com.android.beertracker.integrator;

import com.android.beertracker.activity.HarmonyResponse;
import com.android.beertracker.activity.StyleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HarmoniaAPI {

    @GET("/harmonia-por-estilo/:id/")
    Call<HarmonyResponse> getHarmonia(@Query("id") long id);
}
