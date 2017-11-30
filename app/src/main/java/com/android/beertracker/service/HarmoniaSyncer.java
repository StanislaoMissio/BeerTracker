package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.android.beertracker.entity.Harmonia;
import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.HarmoniaManager;

import java.util.ArrayList;
import java.util.List;

public class HarmoniaSyncer extends Syncer{

    private Service service;
    private long codEstilo;

    public HarmoniaSyncer(Service service, long codEstilo){
        this.service = service;
        this.codEstilo = codEstilo;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(Contants.Services.Tag.RESULT_RECIEVER);
        int command = intent.getIntExtra(Contants.Services.Tag.COMMAND, 0);

        HarmoniaManager harmoniaManager = new HarmoniaManager(this.service);

        switch(command){
            case Contants.Services.SyncCommand.RESULT_OK:
                harmoniaManager.loadAllHarmoniaForAnStyle(new OperationListener<List<Harmonia>>() {
                    @Override
                    public void onOperationSuccess(List<Harmonia> harmonias) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putParcelableArrayList(Contants.Services.Tag.BULK_LIST,
                                new ArrayList<>(harmonias));
                        receiver.send(Contants.Services.Status.FINISH, bundleExtras);
                    }

                    @Override
                    public void onOperationError(List<Harmonia> harmonias, List<OperationError> errors) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putString(Contants.Services.Tag.ERROR_MESSAGE,
                                "Serviço não disponivel. Tente mais tarde");
                        receiver.send(Contants.Services.Status.ERROR, bundleExtras);
                    }
                }, codEstilo);
                break;
        }
    }
}
