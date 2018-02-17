package com.android.beertracker.integrator;

import com.android.beertracker.activity.StyleResponse;
import com.android.beertracker.entity.Style;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EstiloAPI {

    @GET("estilos/")
    Call<StyleResponse> getAllEstilos();
}
