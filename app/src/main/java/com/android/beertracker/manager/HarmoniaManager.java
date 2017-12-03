package com.android.beertracker.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.android.beertracker.business.HarmoniaBusiness;
import com.android.beertracker.entity.Harmonia;
import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.HarmoniaIntegrator;

import java.util.ArrayList;
import java.util.List;

public class HarmoniaManager extends BaseManager {

    private HarmoniaBusiness harmoniaBusiness;

    public HarmoniaManager(Context context){
        HarmoniaIntegrator harmoniaIntegrator = new HarmoniaIntegrator(context);
        harmoniaBusiness = new HarmoniaBusiness(harmoniaIntegrator);
    }

    public void loadAllHarmoniaForAnStyle(final OperationListener operationListener, final long codEstilo){
        final AsyncTask<Void, Void, OperationResult<List<Harmonia>>> task =
                new AsyncTask<Void, Void, OperationResult<List<Harmonia>>>() {
                    @Override
                    protected OperationResult<List<Harmonia>> doInBackground(Void... voids) {
                        return harmoniaBusiness.loadAllHamoniaForAnStyle(codEstilo);
                    }

                    @Override
                    protected void onPostExecute(OperationResult<List<Harmonia>> listOperationResult) {
                        super.onPostExecute(listOperationResult);
                        removeFromTaskList(this);
                        if(listOperationResult == null){
                            List<OperationError> errors = new ArrayList<>();
                            errors.add(new OperationError(Contants.ErrorCodes.ERROR_PLACES_UNAVAILABLE,
                                    "Resultado não pode ser nulo", null));
                            operationListener.onOperationError(null, errors);
                        } else {
                            if (listOperationResult.isOperationCompletedSuccessfully()) {
                                operationListener.onOperationSuccess(listOperationResult.getResult());
                            } else {
                                operationListener.onOperationError(listOperationResult.getResult(),
                                        listOperationResult.getOperationErrors());
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
