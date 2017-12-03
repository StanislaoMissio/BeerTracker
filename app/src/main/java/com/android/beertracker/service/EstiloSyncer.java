package com.android.beertracker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.infrastructure.OperationError;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.StyleManager;

import java.util.ArrayList;
import java.util.List;

public class EstiloSyncer extends Syncer{

    Service service;

    public EstiloSyncer(Service service){
        this.service = service;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(Constants.Services.Tag.RESULT_RECIEVER);
        int command = intent.getIntExtra(Constants.Services.Tag.COMMAND, 0);

        StyleManager styleManager = new StyleManager(this.service);

        switch (command) {
            case Constants.Services.SyncCommand.RESULT_OK:
                styleManager.loadAllEstilos(new OperationListener<List<Style>>() {
                    @Override
                    public void onOperationSuccess(List<Style> styles) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putParcelableArrayList(Constants.Services.Tag.BULK_LIST,
                                new ArrayList<>(styles));
                        receiver.send(Constants.Services.Status.FINISH, bundleExtras);
                    }

                    @Override
                    public void onOperationError(List<Style> styles, List<OperationError> errors) {
                        final Bundle bundleExtras = new Bundle();
                        bundleExtras.putString(Constants.Services.Tag.ERROR_MESSAGE,
                                "Serviço não disponivel. Tente mais tarde");
                        receiver.send(Constants.Services.Status.ERROR, bundleExtras);
                    }
                });
                break;
        }
    }
}
