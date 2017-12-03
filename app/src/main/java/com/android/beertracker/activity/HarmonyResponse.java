package com.android.beertracker.activity;

import com.android.beertracker.entity.Harmony;
import com.android.beertracker.entity.Style;

import java.util.List;

public class HarmonyResponse {

    private List<Harmony> data;

    public List<Harmony> getData() {
        return data;
    }

    public void setData(List<Harmony> data) {
        this.data = data;
    }


}
