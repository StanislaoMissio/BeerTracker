package com.android.beertracker.API;

import com.android.beertracker.controller.RetrofitClient;
import com.android.beertracker.service.HarmoniaService;

public class HarmoniaAPI {

    private static final String BASE_URL = "localhost:5000/";

    public static HarmoniaService getService() {
        return RetrofitClient.getClient(BASE_URL).create(HarmoniaService.class);
    }

}
