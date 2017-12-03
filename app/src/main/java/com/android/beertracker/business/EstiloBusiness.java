package com.android.beertracker.business;

import com.android.beertracker.entity.Style;
import com.android.beertracker.integrator.EstiloIntegrator;
import com.android.beertracker.infrastructure.OperationResult;

import java.util.List;

public class EstiloBusiness {

    private EstiloIntegrator estiloIntegrator;

    public EstiloBusiness(EstiloIntegrator integrator) {
        this.estiloIntegrator = integrator;
    }

    public OperationResult<List<Style>> loadAllEstilos() {
        OperationResult<List<Style>> result = new OperationResult<>();
        result.setResult(estiloIntegrator.loadAllEstilos());
        result.setOperationCompletedSuccessfully(true);
        return result;
    }
}
