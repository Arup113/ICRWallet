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

package com.nurdcoder.android.icr_wallet.data.remote.parseapi;

import android.content.Context;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignInCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignUpCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ProfileUpdateCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResetPasswordCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResponseCallBack;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.UserCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.UserVerifiedCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.RemoteObject;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;
import com.nurdcoder.android.util.lib.GSonHelper;
import com.parse.FunctionCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

public class ParseManager {

    private static volatile ParseManager ourInstance;
    private static Object mutex = new Object();
    Context context;

    private ParseManager(Context context) {
        this.context = context;
    }

    public synchronized static void init(Context context, String serverUrl, String appId, String clientKey) {
        synchronized (mutex) {
            if (ourInstance == null)
                ourInstance = new ParseManager(context);
        }

        Parse.initialize(new Parse.Configuration.Builder(context)
                        .applicationId(appId)
                        .clientKey(clientKey)
                        .server(serverUrl)
                        .build()


//        Parse.initialize(new Parse.Configuration.Builder(context)
//                .applicationId(appId)
//                .server(serverUrl)
//                .build()
        );
    }

    public static ParseManager on() {
        return ourInstance;
    }

    public void signUp(String username, String password, RemoteObject remoteObject, final AuthSignUpCallback callback) {
        ParseUser user = new ParseMapper().getParseUser(username, password, remoteObject);

        // other fields can be set just like with ParseObject

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    //userEntity.setSuccess(true);
                    callback.onSignUp(true, context.getString(R.string.sign_up_success_check_email));
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    //userEntity.setSuccess(false);
                    callback.onSignUp(false, e.getMessage());
                }

            }
        });
    }

    public void updateUserProfile(RemoteObject remoteObject, final ProfileUpdateCallback callback) {
        ParseUser user = ParseUser.getCurrentUser();
        for (String key : remoteObject.keySet()) {
            user.put(key, remoteObject.get(key));
        }

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Profile successfully updated
                    callback.onProfileUpdated(true, context.getString(R.string.profile_updated));
                } else {
                    // Profile update exception
                    callback.onProfileUpdated(false, e.getMessage());
                }
            }
        });
    }

    public void getCurrentUser(UserCallback callback) {
        UserModel userModel = null;

        userModel = new ParseMapper().getUser(ParseUser.getCurrentUser(), userModel);

        callback.onCurrentUser(userModel);
    }

    public void signIn(String email, String password, final AuthSignInCallback callback) {

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {

                UserModel userModel = null;

                if (user != null) {
                    userModel = new ParseMapper().getUser(user, userModel);

                    callback.onSignIn(userModel, true, context.getString(R.string.text_successful));
                } else {
                    callback.onSignIn(userModel, false, e.getMessage());
                }
            }
        });

    }

    public void logOut() {
        ParseUser.logOut();
    }

    public void LiveQuerySubscribe(String user) {

    }

    /**
     * Verify user
     *
     * @param urlString verification url
     */
    public void verifyUser(String urlString, UserVerifiedCallback userVerifiedCallback) {
        //SignUpModelVerification signUpModelVerification = new SignUpModelVerification();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                InputStreamReader read = new InputStreamReader(it);
                BufferedReader buff = new BufferedReader(read);
                StringBuilder dta = new StringBuilder();
                String chunks;
                while ((chunks = buff.readLine()) != null) {
                    dta.append(chunks);
                }

                userVerifiedCallback.onUserVerified(true, context.getString(R.string.text_successful));

            } else {
                userVerifiedCallback.onUserVerified(true, context.getString(R.string.something_went_wrong));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            userVerifiedCallback.onUserVerified(false, e.getMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
            userVerifiedCallback.onUserVerified(false, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            userVerifiedCallback.onUserVerified(false, e.getMessage());
        }
    }

    /**
     * Reseting password
     *
     * @param email
     * @param callback
     */
    public void resetpassword(String email, ResetPasswordCallback callback) {
        ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    callback.onResetPasswordRequest(true, context.getString(R.string.password_reset_email));
                } else {
                    callback.onResetPasswordRequest(false, e.getMessage());
                }
            }
        });
    }

    /**
     * Retrieve response when response is a single object
     *
     * @param endpoint
     * @param modelClass
     * @param parameters
     * @param responseCallback
     * @param <T>
     */
    public <T> void getParseResponse(String endpoint, Class<T> modelClass, HashMap<String, Object> parameters, ResponseCallBack responseCallback) {
        ParseCloud.callFunctionInBackground(endpoint, parameters, new FunctionCallback<T>() {
            @Override
            public void done(T object, ParseException e) {
                if (e == null) {
                    responseCallback.onResponseReceived(GSonHelper.mapToObject((HashMap<Object, Object>) object, modelClass), modelClass, parameters, true, "");
                }
            }
        });
    }
}
