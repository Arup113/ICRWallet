package com.nurdcoder.android.icr_wallet.ui.sign_up;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nurdcoder.android.ICRWalletApp;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInPresenter;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.helper.ShowLog;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignUpCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.RemoteObject;
import com.nurdcoder.android.icr_wallet.ui.base.BasePresenter;
import com.nurdcoder.android.util.helper.CommonUtils;
import com.nurdcoder.android.util.helper.ImageProcessingUtil;
import com.nurdcoder.android.util.helper.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/1/2018 at 1:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: Presenter class
 * Last edited by : Rezwanur on 10/15/2018.
 * Last Reviewed by : Rezwanur on 10/15/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */


public class SignUpPresenter extends BasePresenter<SignUpMvpView> {
    private final static String TAG = SignUpPresenter.class.getSimpleName();

    Context context;

    public SignUpPresenter(Context context) {
        this.context = context;
    }

    /**
     * Sign up with parse
     *
     * @param name
     * @param email
     * @param password
     */
    public void signUp(String name, String email, String password, Uri imageUri) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Endpoints.Flags.SIGN_UP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ShowLog.e(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response.toString());
                    String message = jObj.getString("message");
                    if (message.contains("Successfull")) {
                        getMvpView().onSignUp(true, message);
                    } else {
                        getMvpView().onSignUp(false, message);
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
                    getMvpView().onSignUp(false, "JSONException found");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                ShowLog.e(TAG, "Error: " + error.getMessage());
                getMvpView().onSignUp(false, "");
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
                params.put("name", name);
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
     * Validation of user input
     *
     * @param name
     * @param email
     * @param password
     * @param confirmPass
     */
    public void userInputValidation(String name, String email, String password, String confirmPass) {

        boolean isValid = true;
        String message = "";

        if (!password.equals(confirmPass)) {
            isValid = false;
            message = context.getString(R.string.password_doesnt_match);
        }

        if (TextUtils.isEmpty(password)) {
            isValid = false;
            message = context.getString(R.string.empty_password);
        } else if (!TextUtils.isEmpty(password) &&
                (password.length() < getActivity().getResources().getInteger(R.integer.min_password_length) ||
                        password.length() > getActivity().getResources().getInteger(R.integer.max_password_length))) {
            isValid = false;
            message = context.getString(R.string.password_character_limit);
        }

        if (TextUtils.isEmpty(email)) {
            isValid = false;
            message = context.getString(R.string.empty_email);
        } else if (!TextUtils.isEmpty(email) && !CommonUtils.isEmailValid(email)) {
            isValid = false;
            message = context.getString(R.string.error_invalid_email);
        }

        if (TextUtils.isEmpty(name)) {
            isValid = false;
            message = context.getString(R.string.username_is_invalid);
        } else if (!TextUtils.isEmpty(name) &&
                (name.length() < getActivity().getResources().getInteger(R.integer.min_username_length) ||
                        name.length() > getActivity().getResources().getInteger(R.integer.max_username_length))) {
            isValid = false;
            message = context.getString(R.string.username_character_limit);
        }

        getMvpView().isValidInput(isValid, message);
    }
}
