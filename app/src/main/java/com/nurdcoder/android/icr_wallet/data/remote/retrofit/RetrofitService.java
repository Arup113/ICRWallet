package com.nurdcoder.android.icr_wallet.data.remote.retrofit;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by: Mithun Sarker on 8/31/18 at 10:55 AM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: <Purpose of code>
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface RetrofitService {
    @POST("functions/{endpoint}")
    Call<JsonObject> callApi(@Path("endpoint") String endpoint, @Body HashMap<String, Object> body);
}
