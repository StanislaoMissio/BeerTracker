package com.android.beertracker.service;

import android.content.Intent;

/**
 * Created by stanislao on 13/11/17.
 */

public abstract class Syncer {
    public abstract void sync(Intent intent);
}
