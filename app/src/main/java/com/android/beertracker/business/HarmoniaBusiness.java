package com.android.beertracker.business;

import com.android.beertracker.entity.Harmony;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.HarmoniaIntegrator;

import java.util.List;

/**
 * Created by stanislao on 30/11/17.
 */

public class HarmoniaBusiness {

    private HarmoniaIntegrator harmoniaIntegrator;

    public HarmoniaBusiness(HarmoniaIntegrator harmoniaIntegrator){
        this.harmoniaIntegrator = harmoniaIntegrator;
    }

    public OperationResult<List<Harmony>> loadAllHamoniaForAnStyle(Style style){
        OperationResult<List<Harmony>> result = new OperationResult<>();
        result.setResult(harmoniaIntegrator.loadAllHarmoniaForAnStyle(style));
        result.setOperationCompletedSuccessfully(true);
        return result;
    }
}
