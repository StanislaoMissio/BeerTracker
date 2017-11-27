package com.android.beertracker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.beertracker.R;
import com.android.beertracker.adapter.EstiloAdapter;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.EstiloManager;

import java.util.List;

public class EstiloActivity extends AppCompatActivity implements EstiloAdapter.onEstiloClickListener{

    private EstiloManager estiloManager;
    private RecyclerView recyclerView;
    EstiloAdapter estiloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo);

        estiloManager = new EstiloManager(this);

        recyclerView = findViewById(R.id.recycler_styles);
        recyclerView.setHasFixedSize(true);

         RecyclerView.LayoutManager recyclerLayoutManager =
                new StaggeredGridLayoutManager(3,1);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        loadAllEstilos();
    }

    private void loadAllEstilos(){
        estiloManager.loadAllEstilos(false, new OperationListener<List<Estilo>>() {
            @Override
            public void onOperationSuccess(List<Estilo> estilos) {
                estiloAdapter = new EstiloAdapter(estilos, EstiloActivity.this);
                recyclerView.setAdapter(estiloAdapter);
            }
        });
    }


    @Override
    public void onEstiloSelected(Estilo estilo) {
        Intent intent = new Intent(this, EstiloDetailPageActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(EstiloDetailPageActivity.SELECTED_ESTILO, estilo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
