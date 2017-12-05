package com.android.beertracker.integrator;

import com.android.beertracker.activity.HarmonyResponse;
import com.android.beertracker.entity.Style;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HarmoniaAPI {

    @POST("/harmonia-por-estilo/")
    Call<HarmonyResponse> getHarmonia(@Body Style style);
}
