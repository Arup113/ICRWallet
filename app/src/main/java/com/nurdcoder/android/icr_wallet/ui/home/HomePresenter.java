/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.nurdcoder.android.icr_wallet.ui.home;

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
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {

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
                            getMvpView().onHomeDataLoadedFailed();
                        } else {
                            if (response.getMessage().contains("Found")) {
                                getMvpView().onHomeDataLoadedSuccessful(response);
                            } else {
                                getMvpView().onHomeDataLoadedFailed();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getMvpView() == null) {
                    return;
                }
                getMvpView().onHomeDataLoadedFailed();
            }
        }, ApiResponse.class);

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.Integer.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ICRWalletApp.getInstance().addToRequestQueue(objectRequest);
    }
}