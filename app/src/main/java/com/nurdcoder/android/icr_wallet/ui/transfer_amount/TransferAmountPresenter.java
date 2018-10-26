package com.nurdcoder.android.icr_wallet.ui.transfer_amount;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nurdcoder.android.ICRWalletApp;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.home.ApiResponse;
import com.nurdcoder.android.icr_wallet.ui.base.BasePresenter;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.lib.volley.ObjectRequest;

import java.util.HashMap;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 8/8/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : Presenter for Similar Apps.
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 8/8/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 8/8/18.
 * ****************************************************************************
 */

public class TransferAmountPresenter extends BasePresenter<TransferAmountMvpView> {

    public void getBalance() {
        HashMap<String, String> params = new HashMap<>();
        params.put(Endpoints.Keys.TOKEN, SharedPreferencesManager.getStringSetting(getActivity(), PreferenceKey.KEY_USER_TOKEN, ""));
        HashMap<String, String> headers = new HashMap<>();

        ObjectRequest<ApiResponse> objectRequest = new ObjectRequest<>(Request.Method.POST, Endpoints.Flags.BALANCE, headers, params,
                new Response.Listener<ApiResponse>() {
                    @Override
                    public void onResponse(ApiResponse response) {
                        if (getMvpView() == null) {
                            return;
                        }

                        if (response == null) {
                            getMvpView().onTransferAmountFailed();
                        } else {
                            if (response.getMessage().contains("Found")) {
                                getMvpView().onTransferAmountSuccessful(response);
                            } else {
                                getMvpView().onTransferAmountFailed();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getMvpView() == null) {
                    return;
                }
                getMvpView().onTransferAmountFailed();
            }
        }, ApiResponse.class);

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.Integer.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ICRWalletApp.getInstance().addToRequestQueue(objectRequest);
    }
}