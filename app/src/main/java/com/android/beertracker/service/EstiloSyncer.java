package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.ResultReceiver;

import com.android.beertracker.infrastructure.Contants;

public class EstiloSyncer extends Syncer{

    Service service;

    public EstiloSyncer(Service service){
        this.service = service;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(Contants.Services.Tag.RESULT_RECIEVER);
    }
}
