package com.android.beertracker.utils;

import com.android.beertracker.controller.RetrofitClient;
import com.android.beertracker.service.HarmoniaService;

public class APIUtils {

    private static final String BASE_URL = "localhost:5000/";

    public static HarmoniaService getService() {
        return RetrofitClient.getClient(BASE_URL).create(HarmoniaService.class);
    }
}
