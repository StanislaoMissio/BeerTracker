package com.android.beertracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.fragment.EstiloDetailFragment;
import com.android.beertracker.fragment.EstiloHarmonyFragment;
import com.android.beertracker.fragment.EstiloPriceFragment;

import java.util.ArrayList;
import java.util.List;

public class EstiloDetailPageActivity extends AppCompatActivity {

    public static final String SELECTED_ESTILO = "selected_estilo";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo_detail_page);
        Toolbar toolbar = findViewById(R.id.toolbar_detail_style);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Estilo estilo = loadEstilo();
        if(estilo != null){
            final ViewPager viewPager = findViewById(R.id.pager_selected_style);
            setupViewPager(viewPager);
            TabLayout tabLayout = findViewById(R.id.tab_menu_details_estilo);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_description_white_36dp);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_attach_money_white_36dp);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_restaurant_white_36dp);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EstiloDetailFragment());
        adapter.addFragment(new EstiloPriceFragment());
        adapter.addFragment(new EstiloHarmonyFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Nullable
    private Estilo loadEstilo(){
        if(getIntent().getExtras()!= null
                && getIntent().getExtras().containsKey(SELECTED_ESTILO)){
            return getIntent().getExtras().getParcelable(SELECTED_ESTILO);
        }
        return null;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void addFragment(Fragment fragment){
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
