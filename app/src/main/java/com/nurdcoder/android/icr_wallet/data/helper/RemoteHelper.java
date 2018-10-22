package com.nurdcoder.android.icr_wallet.data.helper;

import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignInCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [23-Jul-2018 at 11:59 AM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [23-Jul-2018 at 11:59 AM].
 * * --> <Second Editor> on [23-Jul-2018 at 11:59 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [23-Jul-2018 at 11:59 AM].
 * * --> <Second Reviewer> on [23-Jul-2018 at 11:59 AM].
 * * ============================================================================
 **/

public class RemoteHelper {
    private static volatile RemoteHelper ourInstance;
    private static Object mutex = new Object();

    private RemoteHelper() {
    }

    public synchronized static void init() {

        synchronized (mutex) {
            if (ourInstance == null)
                ourInstance = new RemoteHelper();
        }
    }

    public static RemoteHelper on() {

        return ourInstance;
    }

    public void signIn(String usernameOrEmail, String password, AuthSignInCallback callback) {


        RemoteApi.on().signIn(usernameOrEmail, password, new AuthSignInCallback() {
            @Override
            public void onSignIn(UserModel userModel, boolean isSuccess, String message) {
                callback.onSignIn(userModel, isSuccess, message);
            }
        });
    }
}
