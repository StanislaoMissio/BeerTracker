package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.android.beertracker.entity.Harmony;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.HarmonyManager;

import java.util.ArrayList;
import java.util.List;

public class HarmoniaSyncer extends Syncer{

    private Service service;
    private Style style;

    public HarmoniaSyncer(Service service, Style style){
        this.service = service;
        this.style = style;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(Constants.Services.Tag.RESULT_RECIEVER);
        int command = intent.getIntExtra(Constants.Services.Tag.COMMAND, 0);

        HarmonyManager harmonyManager = new HarmonyManager(this.service);

        switch(command){
            case Constants.Services.SyncCommand.RESULT_OK:
                harmonyManager.loadAllHarmoniaForAnStyle(new OperationListener<List<Harmony>>() {
                    @Override
                    public void onOperationSuccess(List<Harmony> harmonies) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putParcelableArrayList(Constants.Services.Tag.BULK_LIST,
                                new ArrayList<>(harmonies));
                        receiver.send(Constants.Services.Status.FINISH, bundleExtras);
                    }

                    @Override
                    public void onOperationError(List<Harmony> harmonies, List<OperationError> errors) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putString(Constants.Services.Tag.ERROR_MESSAGE,
                                "Serviço não disponivel. Tente mais tarde");
                        receiver.send(Constants.Services.Status.ERROR, bundleExtras);
                    }
                }, style);
                break;
        }
    }
}
