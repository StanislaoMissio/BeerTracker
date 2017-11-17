package com.android.beertracker.infrastructure;

import android.os.Parcel;
import android.os.Parcelable;

class OperationError implements Parcelable{

    private String errorCode;

    private String errorMessage;

    private String errorData;

    public OperationError(){}

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorData() {
        return errorData;
    }
    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }

    public OperationError(String errorCode, String errorMessage, String errorData){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorData = errorData;
    }

    protected OperationError(Parcel in) {
        errorCode = in.readString();
        errorMessage = in.readString();
        errorData = in.readString();
    }

    public static final Creator<OperationError> CREATOR = new Creator<OperationError>() {
        @Override
        public OperationError createFromParcel(Parcel in) {

            OperationError operationError = new OperationError();
            operationError.errorCode = in.readString();
            operationError.errorMessage = in.readString();
            operationError.errorData = in.readString();

            return operationError;
        }

        @Override
        public OperationError[] newArray(int size) {
            return new OperationError[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(this.errorCode);
        out.writeString(this.errorMessage);
        out.writeString(this.errorData);
    }
}
