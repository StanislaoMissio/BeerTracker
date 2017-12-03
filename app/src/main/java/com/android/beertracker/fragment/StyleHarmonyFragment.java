package com.android.beertracker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.beertracker.R;
import com.android.beertracker.adapter.HarmonyAdapter;
import com.android.beertracker.entity.Style;
import com.android.beertracker.entity.Harmony;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.HarmonyManager;

import java.util.List;

public class StyleHarmonyFragment extends Fragment {

    private final static String SELECTED_STYLE = "selected_style";
    private Context context;
    private HarmonyManager harmonyManager;
    private HarmonyAdapter harmonyAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harmony_style, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_harmony);
        harmonyManager = new HarmonyManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        Style style = loadStyle();
        loadHarmony(style.getCodStyle());
        return rootView;
    }

    private Style loadStyle(){
        if(getActivity().getIntent().getExtras()
                != null && getActivity().getIntent().getExtras().containsKey(SELECTED_STYLE)){
            return getActivity().getIntent().getExtras().getParcelable(SELECTED_STYLE);
        }
        return null;
    }

    private void loadHarmony(long codStyle){
        harmonyManager.loadAllHarmoniaForAnStyle(new OperationListener<List<Harmony>>() {
            @Override
            public void onOperationSuccess(List<Harmony> harmonies) {
                harmonyAdapter = new HarmonyAdapter(harmonies);
                recyclerView.setAdapter(harmonyAdapter);
            }
        }, codStyle);
    }
}
