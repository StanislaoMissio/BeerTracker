package com.android.beertracker.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.android.beertracker.business.UserBusiness;
import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.UserIntegrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager extends BaseManager {

    private UserBusiness userBusiness;

    public UserManager(Context context) {
        UserIntegrator userIntegrator = new UserIntegrator(context);
        userBusiness = new UserBusiness(userIntegrator);
    }

    public void loginUser(final User user, final OperationListener operationListener) {
        final AsyncTask<Void, Void, OperationResult<String>> task =
                new AsyncTask<Void, Void, OperationResult<String>>() {
                    @Override
                    protected OperationResult<String> doInBackground(Void... params) {
                        return userBusiness.loginUser(user);
                    }

                    @Override
                    protected void onPostExecute(OperationResult<String> result) {
                        super.onPostExecute(result);
                        removeFromTaskList(this);
                        if (result == null) {
                            List<OperationError> errors = new ArrayList<>();
                            errors.add(new OperationError(Constants.ErrorCodes.ERROR_PLACES_UNAVAILABLE, "Resultado não pode ser nulo", null));
                            operationListener.onOperationError(null, errors);
                        } else {
                            if (result.isOperationCompletedSuccessfully()) {
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

    public void registerUser(final User user, final OperationListener operationListener) {
        final AsyncTask<Void, Void, OperationResult<String>> task =
                new AsyncTask<Void, Void, OperationResult<String>>() {
                    @Override
                    protected OperationResult<String> doInBackground(Void... params) {
                        return userBusiness.registerUser(user);
                    }

                    @Override
                    protected void onPostExecute(OperationResult<String> result) {
                        super.onPostExecute(result);
                        removeFromTaskList(this);
                        if (result == null) {
                            List<OperationError> errors = new ArrayList<>();
                            errors.add(new OperationError(Constants.ErrorCodes.ERROR_PLACES_UNAVAILABLE, "Resultado não pode ser nulo", null));
                            operationListener.onOperationError(null, errors);
                        } else {
                            if (result.isOperationCompletedSuccessfully()) {
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
