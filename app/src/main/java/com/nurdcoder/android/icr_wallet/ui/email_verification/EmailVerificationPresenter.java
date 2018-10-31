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

package com.nurdcoder.android.icr_wallet.ui.email_verification;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.local.recaptcha.ApiResponse;
import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResetPasswordCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResponseCallBack;
import com.nurdcoder.android.icr_wallet.ui.base.BasePresenter;
import com.nurdcoder.android.util.helper.CommonUtils;
import com.nurdcoder.android.util.helper.Toaster;

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

public class EmailVerificationPresenter extends BasePresenter<EmailVerificationMVPView> implements ResetPasswordCallback, ResponseCallBack {

    /**
     * Verify captcha
     *
     * @param email email
     */
    public void verifyCaptcha(String email) {

        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onPasswordResetRequest(false, "Email is not valid");
        } else {

            SafetyNet.getClient(getActivity()).verifyWithRecaptcha(getActivity().getString(R.string.google_captcha_key))
                    .addOnSuccessListener(getActivity(),
                            response -> {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                String userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    verifyToken(userResponseToken);
                                } else {
                                    getMvpView().onPasswordResetRequest(false, getActivity().getString(R.string.captcha_verification_fail));
                                }
                            })
                    .addOnFailureListener(getActivity(), e -> {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();

                            Toaster.error(getActivity(), "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Toaster.error(getActivity(), "Error: " + e.getMessage());

                            getMvpView().onPasswordResetRequest(false, getActivity().getString(R.string.captcha_verification_fail));
                        }
                    });

        }

    }


    /**
     * @param email email
     */
    public void resetPassword(String email) {
        RemoteApi.on().resetPassword(email, this);
    }


    @Override
    public void onResetPasswordRequest(boolean isSuccess, String message) {
        getMvpView().onPasswordResetRequest(isSuccess, message);
    }

    /**
     * Verify token in parse cloud
     *
     * @param token token
     */
    void verifyToken(String token) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Endpoints.Keys.TOKEN, token);
        RemoteApi.on().getRestApiResponse(Endpoints.Flags.RECAPTCHA, ApiResponse.class, parameters, this);
    }


    @Override
    public <T> void onResponseReceived(Object ts, Class<T> expectedClass, HashMap<String, Object> paramsUsed, boolean isSucees, String message) {
        if (getMvpView() == null) {
            return;
        }

        ApiResponse apiResponse = (ApiResponse) ts;
        getMvpView().onCaptchaVerified((apiResponse.getResult().getResponse().equals("success")), apiResponse.getResult().getResponse());
    }
}
