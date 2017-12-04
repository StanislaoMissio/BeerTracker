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
import com.android.beertracker.entity.Style;
import com.android.beertracker.fragment.StyleDetailFragment;
import com.android.beertracker.fragment.StyleHarmonyFragment;

import java.util.ArrayList;
import java.util.List;

public class StyleDetailPageActivity extends AppCompatActivity {

    public static final String SELECTED_STYLE = "selected_style";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo_detail_page);
        Toolbar toolbar = findViewById(R.id.toolbar_detail_style);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Style style = loadStyle();
        if(style != null){
            final ViewPager viewPager = findViewById(R.id.pager_selected_style);
            setupViewPager(viewPager);
            TabLayout tabLayout = findViewById(R.id.tab_menu_details_style);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_description_white_36dp);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_restaurant_white_36dp);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StyleDetailFragment());
        adapter.addFragment(new StyleHarmonyFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Nullable
    private Style loadStyle(){
        if(getIntent().getExtras()!= null
                && getIntent().getExtras().containsKey(SELECTED_STYLE)){
            return getIntent().getExtras().getParcelable(SELECTED_STYLE);
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
