package com.android.beertracker.integrator;

import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.NetworkOperationResult;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by stanislao on 21/11/17.
 */

public class NetworkIntegrator {

    public NetworkOperationResult fetchData(String url, String contentType, boolean secure){
        URL endPoint;
        HttpURLConnection connection;
        NetworkOperationResult networkOperationResult = null;
        try{
            endPoint = new URL(url);
            if(secure) {
                connection = (HttpsURLConnection) endPoint.openConnection();
            } else {
                connection = (HttpURLConnection) endPoint.openConnection();
            }
            connection.setReadTimeout(Contants.NetworkIntegrator.READ_TIMEOUT);
            connection.setConnectTimeout(Contants.NetworkIntegrator.CONNECT_TIMEOUT);
            connection.setRequestMethod(Contants.NetworkIntegrator.METHOD_GET);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", contentType);
            connection.connect();
            networkOperationResult = new NetworkOperationResult(
                    connection.getResponseCode(),
                    connection.getInputStream());

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return networkOperationResult;
    }
}
