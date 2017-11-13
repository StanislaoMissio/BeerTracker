package com.android.beertracker.infrastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stanislao on 13/11/17.
 */

public class OperationResult<TResult> {

    private boolean operationCompletedSuccessfully;

    private List<OperationError> operationErrors;

    private TResult result;

    public OperationResult() {
        this.operationErrors = new ArrayList<>();
    }

    public boolean isOperationCompletedSuccessfully() {
        return operationCompletedSuccessfully;
    }
    public void setOperationCompletedSuccessfully(boolean operationCompletedSuccessfully) {
        this.operationCompletedSuccessfully = operationCompletedSuccessfully;
    }
    public List<OperationError> getOperationErrors() {
        return operationErrors;
    }
    public void setOperationErrors(List<OperationError> operationErrors) {
        this.operationErrors = operationErrors;
    }
    public TResult getResult() {
        return result;
    }
    public void setResult(TResult result) {
        this.result = result;
    }
}
