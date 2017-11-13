package com.android.beertracker.business;

import com.android.beertracker.integrator.EstiloIntegrator;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.OperationResult;

import java.util.List;

public class EstiloBusiness {

    EstiloIntegrator estiloIntegrator;

    public EstiloBusiness(EstiloIntegrator integrator) {
        this.estiloIntegrator = integrator;
    }

    public OperationResult<List<Estilo>> loadAllEstilos(boolean local) {
        OperationResult<List<Estilo>> result = new OperationResult<>();
        if(local) {
            result.setResult(this.estiloIntegrator.loadEstilosFromDatabase());
        } else {
            List<Estilo> dataFromServer = estiloIntegrator.loadAllEstilos();
            dataFromServer = this.estiloIntegrator.saveAllEstilos(dataFromServer);
            result.setResult(dataFromServer);
        }

        result.setOperationCompletedSuccessfully(true);
        return result;
    }
}
