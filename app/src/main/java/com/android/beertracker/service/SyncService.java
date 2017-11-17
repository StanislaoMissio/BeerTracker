package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.infrastructure.NetworkUtil;

/**
 * Created by stanislao on 13/11/17.
 */

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
            ResultReceiver receiver = intent.getParcelableExtra(Contants.Services.Tag.RESULT_RECIEVER)
        }
    }
}
