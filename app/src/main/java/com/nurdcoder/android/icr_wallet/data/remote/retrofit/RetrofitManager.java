package com.nurdcoder.android.icr_wallet.data.remote.retrofit;

import android.content.Context;

import com.google.gson.JsonObject;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.parse.ParseUser;

import java.io.IOException;
import java.util.HashMap;

import com.nurdcoder.android.icr_wallet.BuildConfig;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.remote.helper.callback.ResponseCallBack;
import com.nurdcoder.android.util.helper.ShowLog;
import com.nurdcoder.android.util.lib.GSonHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by: Mithun Sarker on 8/31/18 at 11:05 AM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Retrofit manager
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
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
