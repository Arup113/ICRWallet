package com.nurdcoder.android.icr_wallet.ui.sign_in;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nurdcoder.android.ICRWalletApp;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.ui.base.BasePresenter;
import com.nurdcoder.android.util.helper.CommonUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.helper.ShowLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 7/31/2018 at 11:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: Presenter class
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 7/31/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class SignInPresenter extends BasePresenter<SignInMvpView> {

    private final static String TAG = SignInPresenter.class.getSimpleName();

    Context context;

    public SignInPresenter(Context context) {
        this.context = context;
    }

    /**
     * Sign in parse
     *
     * @param email
     * @param password
     */
    public void signIn(String email, String password) {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Endpoints.Flags.LOG_IN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ShowLog.e(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response.toString());
                    String message = jObj.getString("message");
                    if (message.contains("Successfull")) {
                        SharedPreferencesManager.setBooleanSetting(getActivity(), PreferenceKey.KEY_IS_LOGGED_IN, true);
                        SharedPreferencesManager.setStringSetting(getActivity(), PreferenceKey.KEY_USER_EMAIL, email);
//                        SharedPreferencesManager.setStringSetting(getActivity(), PreferenceKey.KEY_USER_NAME, jObj.getString("name"));
                        ShowLog.e("token",jObj.getString("token"));
                        SharedPreferencesManager.setStringSetting(getActivity(), PreferenceKey.KEY_USER_TOKEN, jObj.getString("token"));
                        getMvpView().onSignIn(true, message);
                    } else {
                        getMvpView().onSignIn(false, message);
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
//                        builder.setTitle(getActivity().getString(R.string.title_not_success));
//                        builder.setMessage(message);
//                        builder.setPositiveButton(getActivity().getString(R.string.dialog_ok), null);
//                        builder.setPositiveButton(getActivity().getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        builder.setIcon(android.R.drawable.ic_dialog_alert);
//                        builder.show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    getMvpView().onSignIn(false, "JSONException found");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                ShowLog.e(TAG, "Error: " + error.getMessage());
                getMvpView().onSignIn(false, "");
                if (error instanceof NoConnectionError) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
                    builder.setTitle(getActivity().getString(R.string.network_connection_disabled_title));
                    builder.setMessage(getActivity().getString(R.string.network_connection_disabled_content));
                    builder.setPositiveButton(getActivity().getString(R.string.dialog_ok), null);
                    builder.setPositiveButton(getActivity().getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setNegativeButton(getActivity().getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                Constants.Integer.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ICRWalletApp.getInstance().addToRequestQueue(strReq);
    }

    /**
     * Check if user input is valid
     *
     * @param password
     * @param email
     */
    public void validateUserInput(String email, String password) {

        boolean isValid = true;
        String message = "";

        if (TextUtils.isEmpty(password)) {
            isValid = false;
            message = context.getString(R.string.error_invalid_password);
        }

        if (!CommonUtils.isEmailValid(email)) {
            isValid = false;
            message = context.getString(R.string.error_invalid_email);
        }

        getMvpView().isValidUserInput(isValid, message);
    }
}