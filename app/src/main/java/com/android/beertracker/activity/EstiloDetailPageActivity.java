package com.android.beertracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;

public class EstiloDetailPageActivity extends AppCompatActivity {

    public static final String SELECTED_ESTILO = "selected_estilo";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_estilo_detail_page);
        Estilo estilo = loadEstilo();
        if(estilo != null){
            TabLayout tabLayout = findViewById(R.id.tab_menu_details_estilo);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @Nullable
    private Estilo loadEstilo(){
        if(getIntent().getExtras()!= null
                && getIntent().getExtras().containsKey(SELECTED_ESTILO)){
            return getIntent().getExtras().getParcelable(SELECTED_ESTILO);
        }
        return null;
    }
}
