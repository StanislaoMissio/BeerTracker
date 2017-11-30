package com.android.beertracker.activity;

import com.android.beertracker.entity.Estilo;
import com.android.beertracker.entity.Harmonia;

import java.util.List;

public class Response {

    private List<Harmonia> dataHarmonia;
    private List<Estilo> dataEstilos;

    public List<Harmonia> getDataHarmonia() {
        return dataHarmonia;
    }

    public void setDataHarmonia(List<Harmonia> dataHarmonia) {
        this.dataHarmonia = dataHarmonia;
    }

    public List<Estilo> getDataEstilos() {
        return dataEstilos;
    }

    public void setDataEstilos(List<Estilo> dataEstilos) {
        this.dataEstilos = dataEstilos;
    }


}
