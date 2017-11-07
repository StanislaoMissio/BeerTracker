package com.android.beertracker.service;

import com.android.beertracker.model.Estilo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EstiloService {

    @GET("/estilo/")
    Call<Estilo> getEstilos(@Query("id") int id);

    @POST("/inserir-estilo/")
    void setEstilos(Estilo estilo);
}
