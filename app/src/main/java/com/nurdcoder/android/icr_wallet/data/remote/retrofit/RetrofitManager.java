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

package com.nurdcoder.android.icr_wallet.data.remote.retrofit;

import android.content.Context;

import com.google.gson.JsonObject;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResponseCallBack;
import com.nurdcoder.android.util.helper.ShowLog;
import com.nurdcoder.android.util.lib.GSonHelper;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

public class RetrofitManager {

    private static volatile RetrofitManager ourInstance;
    private static Object mutex = new Object();
    private static Context context;
    private static Retrofit retrofit;

    private RetrofitManager(Context context) {
        RetrofitManager.context = context;
    }

    public synchronized static void init(Context context) {
        synchronized (mutex) {
            if (ourInstance == null)
                ourInstance = new RetrofitManager(context);
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Endpoints.Constants.BASE_URL)
                    .client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    }


    public static OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder builder = request.newBuilder()
//                                .addHeader("X-Parse-Application-Id", context.getString(R.string.parse_app_id))
                                .addHeader("Content-Type", "application/json");
//                                .addHeader("X-Parse-REST-API-Key", context.getString(R.string.parse_rest_api_key))
//                                .addHeader("X-Parse-Session-Token", ParseUser.getCurrentUser() != null ? ParseUser.getCurrentUser().getSessionToken() : "");

                        request = builder.build();

                        return chain.proceed(request);
                    }
                });

        return builder.build();

    }


    public static RetrofitManager on() {
        return ourInstance;
    }


    /**
     * Get api response using retrofit
     *
     * @param endpoint
     * @param modelClass
     * @param parameters
     * @param responseCallback
     * @param <T>
     */
    public <T> void getApiResponse(String endpoint, Class<T> modelClass, HashMap<String, Object> parameters, ResponseCallBack responseCallback) {
        ShowLog.e("endpoint", endpoint);
        ShowLog.e("parameters", parameters.toString());
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<JsonObject> call = service.callApi(endpoint, parameters);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body() != null) {
                    responseCallback.onResponseReceived(GSonHelper.fromJson(response.body().toString(), modelClass), modelClass, parameters, response.isSuccessful(), response.message());
                } else {
                    responseCallback.onResponseReceived(null, modelClass, parameters, false, context.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                responseCallback.onResponseReceived(null, modelClass, parameters, false, t.getMessage());
            }
        });
    }
}
