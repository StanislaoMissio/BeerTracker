package com.android.beertracker.integrator;

import com.android.beertracker.infrastructure.Contants;

public class HarmoniaIntegrator {

    public static HarmoniaAPI getService() {
        return RetrofitClient.getClient(Contants.BeerTrackerAPI.HOST).create(HarmoniaAPI.class);
    }

}
