package com.android.beertracker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.entity.Harmonia;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.HarmoniaManager;

import java.util.List;

public class EstiloHarmonyFragment extends Fragment {

    private final static String SELECTED_ESTILO = "selected_estilo";
    private Context context;
    private HarmoniaManager harmoniaManager;

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
        Harmonia harmonia = loadHarmonia(loadEstilo().getCodEstilo());
        harmoniaManager = new HarmoniaManager(context);
        return rootView;
    }

    private Estilo loadEstilo(){
        if(getActivity().getIntent().getExtras()
                != null && getActivity().getIntent().getExtras().containsKey(SELECTED_ESTILO)){
            return getActivity().getIntent().getExtras().getParcelable(SELECTED_ESTILO);
        }
        return null;
    }

    private Harmonia loadHarmonia(long codEstilo){
        harmoniaManager.loadAllHarmoniaForAnStyle(new OperationListener<List<Harmonia>>() {
            @Override
            public void onOperationSuccess(List<Harmonia> harmonias) {

            }
        }, codEstilo);
    }
}
