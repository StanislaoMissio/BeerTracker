package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.android.beertracker.infrastructure.Constants;
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
        int command = intent.getIntExtra(Constants.Services.Tag.COMMAND, 0);

        if(!NetworkUtil.isConnectionAvailable(this)) {
            ResultReceiver receiver = intent.getParcelableExtra(Constants.Services.Tag.RESULT_RECIEVER);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.Services.Tag.ERROR_MESSAGE, "Sem conexão com a internet");
            receiver.send(Constants.Services.Status.ERROR, bundle);
            return START_NOT_STICKY;
        }

        switch(command){
            case Constants.Services.SyncCommand.RESULT_OK: {
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
