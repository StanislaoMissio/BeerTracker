package com.android.beertracker.infrastructure;

import java.util.List;

/**
 * Created by stanislao on 13/11/17.
 */

public abstract class OperationListener<TResult> {

    public abstract void onOperationSuccess(TResult result);

    public void onOperationError(TResult result, List<OperationError> errors) {}

    public void onOperationCancelled() {}
}
