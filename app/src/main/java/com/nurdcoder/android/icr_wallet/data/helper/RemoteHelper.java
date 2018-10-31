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

package com.nurdcoder.android.icr_wallet.data.helper;

import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignInCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;

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
