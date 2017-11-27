package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.NetworkUtil;

public class SyncService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Syncer syncer;
        int command = intent.getIntExtra(Contants.Services.Tag.COMMAND, 0);

        if(!NetworkUtil.isConnectionAvailable(this)) {
            ResultReceiver receiver = intent.getParcelableExtra(Contants.Services.Tag.RESULT_RECIEVER);
            Bundle bundle = new Bundle();
            bundle.putString(Contants.Services.Tag.ERROR_MESSAGE, "Sem conexão com a internet");
            receiver.send(Contants.Services.Status.ERROR, bundle);
            return START_NOT_STICKY;
        }

        switch(command){
            case Contants.Services.SyncCommand.ESTILO_ALL: {
                syncer = new EstiloSyncer(this);
                syncer.sync(intent);
                break;
            }
            default: {
                throw  new IllegalArgumentException("O comando " + command + "não é valido");
            }
        }
        return START_NOT_STICKY;
    }
}
