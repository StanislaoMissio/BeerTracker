package com.android.beertracker.integrator;

import android.content.Context;

import com.android.beertracker.activity.UserResponse;
import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.Constants;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by DELL on 03/12/2017.
 */

public class UserIntegrator extends BaseIntegrator {

    public UserIntegrator(Context context) {
        super(context);
    }

    public String registerUser(HashMap<String, String> user) {
        final UserAPI api = RetrofitClient.getClient(Constants.BeerTrackerAPI.HOST).create(UserAPI.class);
        final Call<UserResponse> request = api.registerUsuario(user);
        try {
            final Response<UserResponse> response = request.execute();
            return response.body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String loginUser(User user) {
        final UserAPI api = RetrofitClient.getClient(Constants.BeerTrackerAPI.HOST).create(UserAPI.class);
        final Call<UserResponse> request = api.loginUser(user);
        try{
            final Response<UserResponse> response = request.execute();
            return response.body().getData();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
