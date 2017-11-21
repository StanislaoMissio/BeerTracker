package com.android.beertracker.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.beertracker.R;
import com.android.beertracker.adapter.EstiloAdapter;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.EstiloManager;

import java.util.List;

public class EstiloActivity extends Activity {

    private EstiloManager estiloManager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private Context context;
    EstiloAdapter estiloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo);

        RecyclerView recyclerView = findViewById(R.id.recycler_styles);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }

    private void loadAllEstilos(final boolean local){
        estiloManager.loadAllEstilos(local, new OperationListener<List<Estilo>>() {
            @Override
            public void onOperationSuccess(List<Estilo> estilos) {

            }
        });
    }
}
