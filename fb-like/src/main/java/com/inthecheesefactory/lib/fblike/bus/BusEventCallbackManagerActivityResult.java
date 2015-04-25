package com.inthecheesefactory.lib.fblike.bus;

import android.content.Intent;

/**
 * Created by nuuneoi on 4/25/2015.
 */
public class BusEventCallbackManagerActivityResult {

    int requestCode;
    int resultCode;
    Intent data;

    public BusEventCallbackManagerActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
