package com.android.beertracker.activity;

import com.android.beertracker.entity.Estilo;

import java.util.List;

public class Response {

    private List<Estilo> data;

    public List<Estilo> getData() {
        return data;
    }

    public void setData(List<Estilo> data) {
        this.data = data;
    }
}
