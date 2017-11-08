package com.android.beertracker.API;

import com.android.beertracker.controller.RetrofitClient;
import com.android.beertracker.service.EstiloService;

public class EstiloAPI {

    private static final String BASE_URL = "localhost:5000/";

    public static EstiloService getEstilo(){
        return RetrofitClient.getClient(BASE_URL).create(EstiloService.class);
    }

}
