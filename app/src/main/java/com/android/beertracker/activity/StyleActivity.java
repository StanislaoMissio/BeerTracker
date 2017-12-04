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
import com.android.beertracker.adapter.StyleAdapter;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.StyleManager;

import java.util.List;

public class StyleActivity extends AppCompatActivity implements StyleAdapter.onStyleClickListener {

    private StyleManager styleManager;
    private RecyclerView recyclerView;
    StyleAdapter styleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo);

        Toolbar toolbar = findViewById(R.id.toolbar_list_style);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        styleManager = new StyleManager(this);

        recyclerView = findViewById(R.id.recycler_styles);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerLayoutManager =
                new StaggeredGridLayoutManager(Constants.Services.Tag.COLUMN_STAGGED_LAYOUT,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.addItemDecoration(new StyleActivity.GridSpacingItemDecoration(2, 50, true));
        loadAllStyles();
    }

    private void loadAllStyles(){
        styleManager.loadAllEstilos(new OperationListener<List<Style>>() {
            @Override
            public void onOperationSuccess(List<Style> styles) {
                styleAdapter = new StyleAdapter(styles, StyleActivity.this);
                recyclerView.setAdapter(styleAdapter);
            }
        });
    }


    @Override
    public void onStyleSelected(Style style) {
        Intent intent = new Intent(this, StyleDetailPageActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(StyleDetailPageActivity.SELECTED_STYLE, style);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
