package com.nurdcoder.android.icr_wallet.data.local.recaptcha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 9/4/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : Data Model Class for App Details API.
 * *
 * * Last Edited by : Rezwanur on 9/25/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : Rezwanur on 9/25/18.
 * ****************************************************************************
 */

public class ApiResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
