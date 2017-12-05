package com.android.beertracker.integrator;

import android.content.Context;

import com.android.beertracker.activity.HarmonyResponse;
import com.android.beertracker.entity.Harmony;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class HarmoniaIntegrator extends BaseIntegrator {

    public HarmoniaIntegrator(Context context) {
        super(context);
    }

    public List<Harmony> loadAllHarmoniaForAnStyle(Style style) {
        final HarmoniaAPI api = RetrofitClient.getClient(Constants.BeerTrackerAPI.HOST).create(HarmoniaAPI.class);
        final Call<HarmonyResponse> request = api.getHarmonia(style);
        try {
            final retrofit2.Response<HarmonyResponse> response = request.execute();
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
