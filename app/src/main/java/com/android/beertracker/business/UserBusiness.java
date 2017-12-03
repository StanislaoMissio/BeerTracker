package com.android.beertracker.business;

import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.OperationResult;
import com.android.beertracker.integrator.UserIntegrator;

import java.util.HashMap;
import java.util.List;

public class UserBusiness {

    private UserIntegrator userIntegrator;

    public UserBusiness(UserIntegrator userIntegrator){
        this.userIntegrator = userIntegrator;
    }

    public OperationResult<String> loginUser(User user){
        OperationResult<String> result = new OperationResult<>();
        result.setResult(userIntegrator.loginUser(user));
        result.setOperationCompletedSuccessfully(true);
        return result;
    }

    public OperationResult<String> registerUser(HashMap<String, String> user){
        OperationResult<String> result = new OperationResult<>();
        result.setResult(userIntegrator.registerUser(user));
        result.setOperationCompletedSuccessfully(true);
        return result;
    }
}
