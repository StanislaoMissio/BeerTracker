package com.android.beertracker.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.beertracker.R;
import com.android.beertracker.adapter.EstiloAdapter;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.Contants;
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

        Toolbar toolbar = findViewById(R.id.toolbar_list_style);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        estiloManager = new EstiloManager(this);

        recyclerView = findViewById(R.id.recycler_styles);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerLayoutManager =
                new StaggeredGridLayoutManager(Contants.Services.Tag.COLUMN_STAGGED_LAYOUT,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.addItemDecoration(new EstiloActivity.GridSpacingItemDecoration(2, 50, true));
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

    class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}
