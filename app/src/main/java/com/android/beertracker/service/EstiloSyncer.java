package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.EstiloManager;

import java.util.ArrayList;
import java.util.List;

public class EstiloSyncer extends Syncer{

    Service service;

    public EstiloSyncer(Service service){
        this.service = service;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(Contants.Services.Tag.RESULT_RECIEVER);
        int command = intent.getIntExtra(Contants.Services.Tag.COMMAND, 0);

        EstiloManager estiloManager = new EstiloManager(this.service);

        switch (command) {
            case Contants.Services.SyncCommand.ESTILO_ALL:
                estiloManager.loadAllEstilos(false, new OperationListener<List<Estilo>>() {
                    @Override
                    public void onOperationSuccess(List<Estilo> estilos) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putParcelableArrayList(Contants.Services.Tag.BULK_LIST_ESTILO,
                                new ArrayList<>(estilos));
                        receiver.send(Contants.Services.Status.FINISH, bundleExtras);
                    }

                    @Override
                    public void onOperationError(List<Estilo> estilos, List<OperationError> errors) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putString(Contants.Services.Tag.ERROR_MESSAGE,
                                "Serviço não disponivel. Tente mais tarde");
                        receiver.send(Contants.Services.Status.ERROR, bundleExtras);
                    }
                });
                break;
        }
    }
}
