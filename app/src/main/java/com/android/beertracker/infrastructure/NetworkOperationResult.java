package com.android.beertracker.infrastructure;

import java.io.InputStream;

/**
 * Created by stanislao on 21/11/17.
 */

public class NetworkOperationResult {

    private int responseCode;
    private InputStream stream;

    public NetworkOperationResult(final int responseCode, final InputStream stream){
        this.responseCode = responseCode;
        this.stream = stream;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
}
