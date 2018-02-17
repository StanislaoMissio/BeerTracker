package com.android.beertracker.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.android.beertracker.business.HarmoniaBusiness;
import com.android.beertracker.entity.Harmony;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.HarmoniaIntegrator;

import java.util.ArrayList;
import java.util.List;

public class HarmonyManager extends BaseManager {

    private HarmoniaBusiness harmoniaBusiness;

    public HarmonyManager(Context context){
        HarmoniaIntegrator harmoniaIntegrator = new HarmoniaIntegrator(context);
        harmoniaBusiness = new HarmoniaBusiness(harmoniaIntegrator);
    }

    public void loadAllHarmoniaForAnStyle(final OperationListener operationListener, final Style style){
        final AsyncTask<Void, Void, OperationResult<List<Harmony>>> task =
                new AsyncTask<Void, Void, OperationResult<List<Harmony>>>() {
                    @Override
                    protected OperationResult<List<Harmony>> doInBackground(Void... voids) {
                        return harmoniaBusiness.loadAllHamoniaForAnStyle(style);
                    }

                    @Override
                    protected void onPostExecute(OperationResult<List<Harmony>> listOperationResult) {
                        super.onPostExecute(listOperationResult);
                        removeFromTaskList(this);
                        if(listOperationResult == null){
                            List<OperationError> errors = new ArrayList<>();
                            errors.add(new OperationError(Constants.ErrorCodes.ERROR_PLACES_UNAVAILABLE,
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
