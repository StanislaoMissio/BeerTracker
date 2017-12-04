package com.android.beertracker.integrator;

import com.google.gson.Gson;

import com.android.beertracker.activity.UserResponse;
import com.android.beertracker.entity.User;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/register-user")
    Call<UserResponse> registerUsuario(@Body User user);

    @POST("/login-user")
    Call<UserResponse> loginUser(@Body User user);
}
