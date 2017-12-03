package com.android.beertracker.integrator;

import com.android.beertracker.activity.Response;
import com.android.beertracker.entity.Estilo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EstiloAPI {

    @GET("/estilo/:id")
    Call<Response> getEstilo(@Query("id") int id);

    @GET("estilos/")
    Call<Response> getAllEstilos();

    @POST("/inserir-estilo/")
    void setEstilos(Estilo estilo);
}
