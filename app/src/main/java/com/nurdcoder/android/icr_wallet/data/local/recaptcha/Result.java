package com.nurdcoder.android.icr_wallet.data.local.recaptcha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by: Mithun Sarker on 8/30/18 at 11:03 AM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Result response
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class Result {

    @SerializedName("response")
    @Expose
    String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
