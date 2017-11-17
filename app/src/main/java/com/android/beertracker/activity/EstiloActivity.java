package com.android.beertracker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.beertracker.R;

public class EstiloActivity extends Activity {

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
}
