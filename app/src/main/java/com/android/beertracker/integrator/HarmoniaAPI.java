package com.android.beertracker.integrator;

import com.android.beertracker.activity.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HarmoniaAPI {

    @GET("/harmonia-por-estilo/:id/")
    Call<Response> getHarmonia(@Query("id") long id);
}
