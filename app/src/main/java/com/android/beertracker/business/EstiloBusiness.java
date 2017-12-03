package com.android.beertracker.business;

import com.android.beertracker.integrator.EstiloIntegrator;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.OperationResult;

import java.util.List;

public class EstiloBusiness {

    private EstiloIntegrator estiloIntegrator;

    public EstiloBusiness(EstiloIntegrator integrator) {
        this.estiloIntegrator = integrator;
    }

    public OperationResult<List<Estilo>> loadAllEstilos() {
        OperationResult<List<Estilo>> result = new OperationResult<>();
        result.setResult(estiloIntegrator.loadAllEstilos());
        result.setOperationCompletedSuccessfully(true);
        return result;
    }
}
