package com.nurdcoder.android.icr_wallet.data.remote;

import android.content.Context;
import android.os.AsyncTask;

import com.nurdcoder.android.icr_wallet.data.remote.helper.InitManagerAsync;
import com.nurdcoder.android.icr_wallet.data.remote.helper.RemoteApiOption;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignInCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.AuthSignUpCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.FileTransferCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ProfileUpdateCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResetPasswordCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResponseCallBack;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.UserCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.UserVerifiedCallback;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.ChatMessage;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.RemoteObject;
import com.nurdcoder.android.icr_wallet.data.remote.parseapi.ParseManager;
import com.nurdcoder.android.icr_wallet.data.remote.retrofit.RetrofitManager;

import java.io.File;
import java.util.HashMap;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [13-Jul-2018 at 12:12 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [13-Jul-2018 at 12:12 PM].
 * * --> <Second Editor> on [13-Jul-2018 at 12:12 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [13-Jul-2018 at 12:12 PM].
 * * --> <Second Reviewer> on [13-Jul-2018 at 12:12 PM].
 * * ============================================================================
 **/

public class RemoteApi {

    /*RegionStart: Singleton Internal */
    private static volatile RemoteApi ourInstance;
    private static Object mutex = new Object();

    private RemoteApi(Context context, RemoteApiOption remoteApiOption) {
        new InitManagerAsync(context, remoteApiOption).execute();
    }

    public synchronized static void init(Context context, RemoteApiOption remoteApiOption) {
        synchronized (mutex) {
            if (ourInstance == null)
                ourInstance = new RemoteApi(context, remoteApiOption);
        }
    }

    public static RemoteApi on() {
        return ourInstance;
    }

    /*RegionEnd: Singleton Internal */

    /*RegionStart: Public API */

    public void signUp(String username, String password, RemoteObject remoteObject, AuthSignUpCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().signUp(username, password, remoteObject, callback));
    }

    public void updateProfile(RemoteObject remoteObject, ProfileUpdateCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().updateUserProfile(remoteObject, callback));
    }

    public void signIn(String email, String password, AuthSignInCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().signIn(email, password, callback));
    }

    public void logOut() {
        AsyncTask.execute(() -> ParseManager.on().logOut());
    }


    public void verifyUser(String url, UserVerifiedCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().verifyUser(url, callback));
    }

    public void resetPassword(String email, ResetPasswordCallback resetPasswordCallback) {
        AsyncTask.execute(() -> ParseManager.on().resetpassword(email, resetPasswordCallback));
    }

    public void dynamicLink() {
//        AsyncTask.execute(() -> FireBaseManager.on().dynamicLink());
    }

    public void LiveQuerySubscribe(String user) {
        AsyncTask.execute(() -> ParseManager.on().LiveQuerySubscribe(user));
    }

    public void getCurrentUser(UserCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().getCurrentUser(callback));
    }

    public void sendChatMessage(String user, ChatMessage chatMessage) {
//        AsyncTask.execute(() -> TCharmManager.on().sendMessage(user, chatMessage));
    }

    public void uploadFile(String url, String file_key, File file, FileTransferCallback callback) {
//        AsyncTask.execute(() -> S3Manager.on().uploadFile(url, file_key, file, callback));
    }

    public <T> void getRestApiResponse(String endpoint, Class<T> modelClass, HashMap<String, Object> parameters, ResponseCallBack responseCallback) {
        RetrofitManager.on().getApiResponse(endpoint, modelClass, parameters, responseCallback);
    }
}