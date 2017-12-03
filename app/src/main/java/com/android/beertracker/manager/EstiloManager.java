package com.android.beertracker.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.android.beertracker.business.EstiloBusiness;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.EstiloIntegrator;

import java.util.ArrayList;
import java.util.List;

public class EstiloManager extends BaseManager{

    private EstiloBusiness estiloBusiness;

    public EstiloManager(Context context){
        EstiloIntegrator estiloIntegrator = new EstiloIntegrator(context);
        estiloBusiness = new EstiloBusiness(estiloIntegrator);
    }

    public void loadAllEstilos(final OperationListener operationListener) {
        final AsyncTask<Void, Void, OperationResult<List<Estilo>>> task =
                new AsyncTask<Void, Void, OperationResult<List<Estilo>>>() {
            @Override
            protected OperationResult<List<Estilo>> doInBackground(Void... params) {
                return estiloBusiness.loadAllEstilos();
            }

            @Override
            protected void onPostExecute(OperationResult<List<Estilo>> result) {
                super.onPostExecute(result);
                removeFromTaskList(this);
                if(result == null){
                    List<OperationError> errors = new ArrayList<>();
                    errors.add(new OperationError(Contants.ErrorCodes.ERROR_PLACES_UNAVAILABLE, "Resultado não pode ser nulo", null));
                    operationListener.onOperationError(null, errors);
                } else {
                    if(result.isOperationCompletedSuccessfully()) {
                        operationListener.onOperationSuccess(result.getResult());
                    } else {
                        operationListener.onOperationError(result.getResult(), result.getOperationErrors());
                    }
                }
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                removeFromTaskList(this);
                operationListener.onOperationCancelled();
            }
        };

        addToTaskList(task);
        task.execute();
    }
}
