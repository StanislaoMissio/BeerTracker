package com.android.beertracker.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.beertracker.API.EstiloAPI;
import com.android.beertracker.R;
import com.android.beertracker.adapter.EstiloAdapter;
import com.android.beertracker.model.Estilo;
import com.android.beertracker.service.EstiloService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstiloActivity extends Activity {

    private EstiloService estiloService;
    private EstiloAdapter estiloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo);
        estiloService = EstiloAPI.getEstilo();

        RecyclerView recyclerView = findViewById(R.id.recycler_styles);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        estiloAdapter = new EstiloAdapter(new ArrayList<Estilo>(0));
        recyclerView.setAdapter(estiloAdapter);

        loadEstilos();
    }

    private void loadEstilos() {
        estiloService.getEstilos().enqueue(new Callback<Estilo>() {
            @Override
            public void onResponse(Call<Estilo> call, Response<Estilo> response) {
                if(response.isSuccessful()) {
                    estiloAdapter.updateEstilo(response.body().get);
                } else {
                    int statusCode = response.code();
                    Log.d("Error", "statusCode: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Estilo> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
