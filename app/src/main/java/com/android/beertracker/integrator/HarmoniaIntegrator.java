package com.android.beertracker.integrator;

import android.content.Context;

import com.android.beertracker.activity.Response;
import com.android.beertracker.entity.Harmonia;
import com.android.beertracker.infrastructure.Contants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class HarmoniaIntegrator extends BaseIntegrator{

    public HarmoniaIntegrator(Context context){
        super(context);
    }

    public List<Harmonia> loadAllHarmoniaForAnStyle(long codEstilo){
        final HarmoniaAPI api = RetrofitClient.getClient(Contants.BeerTrackerAPI.HOST).create(HarmoniaAPI.class);
        final Call<Response> request = api.getHarmonia(codEstilo);
        try{
            final retrofit2.Response<Response> response = request.execute();
            return response.body().getDataHarmonia();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
