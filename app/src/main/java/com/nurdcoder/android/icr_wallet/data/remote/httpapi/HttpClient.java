package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import com.nurdcoder.android.util.helper.ShowLog;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : SUDIPTA KUMAR PAIK
* * Date : 2/14/18
* * Email : sudipta@w3engineers.com
* *
* * Purpose: Main class to interact with library. Please don't change without discussion with CTO
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 2/14/18.
* * History:
* * 1:
* * 2:
* * Source: http://loopj.com/android-async-http/
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/14/18.
* ****************************************************************************
*/

class HttpClient {
    private static final String BASE_URL = "https://api.twitter.com/1/";
    static String TAG = "HttpClient skpaik";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /*
    * Create the get request to remote
    * */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        ShowLog.e(TAG, getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /*
    * Create the post request to remote
    * */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        ShowLog.e(TAG, getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /*
    * Get absolute url based on base url and api
    * */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}