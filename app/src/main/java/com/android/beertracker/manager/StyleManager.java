package com.android.beertracker.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.android.beertracker.business.EstiloBusiness;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.EstiloIntegrator;

import java.util.ArrayList;
import java.util.List;

public class StyleManager extends BaseManager{

    private EstiloBusiness estiloBusiness;

    public StyleManager(Context context){
        EstiloIntegrator estiloIntegrator = new EstiloIntegrator(context);
        estiloBusiness = new EstiloBusiness(estiloIntegrator);
    }

    public void loadAllEstilos(final OperationListener operationListener) {
        final AsyncTask<Void, Void, OperationResult<List<Style>>> task =
                new AsyncTask<Void, Void, OperationResult<List<Style>>>() {
            @Override
            protected OperationResult<List<Style>> doInBackground(Void... params) {
                return estiloBusiness.loadAllEstilos();
            }

            @Override
            protected void onPostExecute(OperationResult<List<Style>> result) {
                super.onPostExecute(result);
                removeFromTaskList(this);
                if(result == null){
                    List<OperationError> errors = new ArrayList<>();
                    errors.add(new OperationError(Constants.ErrorCodes.ERROR_PLACES_UNAVAILABLE, "Resultado não pode ser nulo", null));
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
