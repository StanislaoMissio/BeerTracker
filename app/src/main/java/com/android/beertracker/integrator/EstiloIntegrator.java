package com.android.beertracker.integrator;

import android.content.Context;

import com.android.beertracker.activity.StyleResponse;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class EstiloIntegrator extends BaseIntegrator {

    public EstiloIntegrator(Context context) {
        super(context);
    }

    public List<Style> loadAllEstilos() {
        final EstiloAPI api = RetrofitClient.getClient(Constants.BeerTrackerAPI.HOST).create(EstiloAPI.class);
        final Call<StyleResponse> request = api.getAllEstilos();
        try {
            final retrofit2.Response<StyleResponse> response = request.execute();
            return response.body().getData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
