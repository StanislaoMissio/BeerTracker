package com.android.beertracker.business;

import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.UserIntegrator;

public class UserBusiness {

    private UserIntegrator userIntegrator;

    public UserBusiness(UserIntegrator userIntegrator) {
        this.userIntegrator = userIntegrator;
    }

    public OperationResult<String> loginUser(User user) {
        OperationResult<String> result = new OperationResult<>();
        result.setResult(userIntegrator.loginUser(user));
        if (result.getResult().equals("Sucess"))
            result.setOperationCompletedSuccessfully(true);
        else
            result.setOperationCompletedSuccessfully(false);
        return result;
    }

    public OperationResult<String> registerUser(User user) {
        OperationResult<String> result = new OperationResult<>();
        result.setResult(userIntegrator.registerUser(user));
        if (result.getResult().equals("Sucess"))
            result.setOperationCompletedSuccessfully(true);
        else
            result.setOperationCompletedSuccessfully(false);
        return result;
    }
}
