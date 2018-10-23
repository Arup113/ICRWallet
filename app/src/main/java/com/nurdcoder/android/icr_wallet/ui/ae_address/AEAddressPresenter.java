package com.nurdcoder.android.icr_wallet.ui.ae_address;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nurdcoder.android.ICRWalletApp;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.ae_address.ApiResponse;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;
import com.nurdcoder.android.icr_wallet.ui.base.BasePresenter;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.lib.volley.ObjectRequest;

import java.util.HashMap;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/3/2018 at 12:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: Presenter class for {@link AEAddressActivity}
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 9/25/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class AEAddressPresenter extends BasePresenter<AEAddressMVPView> {


    public void aeAddress(int mType, Address mData, String s) {
        HashMap<String, String> params = new HashMap<>();
        params.put(Endpoints.Keys.TOKEN, SharedPreferencesManager.getStringSetting(getActivity(), PreferenceKey.KEY_USER_TOKEN, ""));
        if (mType == Constants.Integer.ONE) {
            params.put(Endpoints.Keys.ADDRESS, mData.getAddress());

        }
        params.put(Endpoints.Keys.LABEL, s);
        HashMap<String, String> headers = new HashMap<>();

        ObjectRequest<ApiResponse> objectRequest = new ObjectRequest<>(Request.Method.POST, Endpoints.Flags.AE_ADDRESS, headers, params,
                new Response.Listener<ApiResponse>() {
                    @Override
                    public void onResponse(ApiResponse response) {
                        if (getMvpView() == null) {
                            return;
                        }

                        if (response == null) {
                            getMvpView().onAeAddressFailed();
                        } else {
                            if (response.getMessage().contains("Update")) {
                                getMvpView().onAeAddressSuccessful(response);
                            } else {
                                getMvpView().onAeAddressFailed();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getMvpView() == null) {
                    return;
                }
                getMvpView().onAeAddressFailed();
            }
        }, ApiResponse.class);

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.Integer.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ICRWalletApp.getInstance().addToRequestQueue(objectRequest);
    }
}
