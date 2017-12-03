package com.android.beertracker.activity;

import com.android.beertracker.entity.Estilo;

import java.util.List;

public class Response {

    private List<Estilo> data;

    public List<Estilo> getDataEstilos() {
        return data;
    }

    public void setDataEstilos(List<Estilo> dataEstilos) {
        this.data = dataEstilos;
    }


}
