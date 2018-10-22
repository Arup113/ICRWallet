package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nurdcoder.android.util.helper.ShowLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/*
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : SUDIPTA KUMAR PAIK
 * * Date : 2/14/18
 * * Email : sudipta@w3engineers.com
 * *
 * * Purpose: App level interface for call remote API
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 2/14/18.
 * * History:
 * * 1:
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/14/18.
 * ****************************************************************************
 */

public class HttpAPI {
    String TAG = "AsyncHandler skpaik";

    /**
     * getData() method for http get method calling
     *
     * @param url           Relative base url path
     * @param params        a list of query parameters
     * @param iHttpCallback a callback method where you get the response
     * @return HttpModel type data via sIHttpCallback callback
     */
    public void getData(String url, RequestParams params, IHttpCallback iHttpCallback) {
        Logs("private void postWifiInfoAdd(): " + params.toString());
        HttpClient.get(url, params, getResponseHandler(iHttpCallback));
    }

    /**
     * getFile() method for http get method calling to download file
     *
     * @param url           Relative base url path
     * @param params        a list of query parameters
     * @param iHttpCallback a callback method where you get the response
     * @return HttpModel type data via sIHttpCallback callback
     */
    public void getFile(String url, RequestParams params, IHttpCallback iHttpCallback, Context context) {
        Logs("private void postWifiInfoAdd(): " + params.toString());
        HttpClient.get(url, params, getFileAsyncHttpResponseHandler(iHttpCallback, context));
    }

    /**
     * postData() method for http post method calling
     *
     * @param url           Relative base url path
     * @param params        a list of query parameters
     * @param iHttpCallback a callback method where you get the response
     * @return HttpModel type data via sIHttpCallback callback
     */
    public void postData(String url, RequestParams params, IHttpCallback iHttpCallback) {
        Logs("private void postWifiInfoAdd(): " + params.toString());
        HttpClient.post(url, params, getResponseHandler(iHttpCallback));
    }

    private AsyncHttpResponseHandler getResponseHandler(final IHttpCallback callback) {

        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Logs("onSuccess JSONObject");
                Logs("onSuccess()  Call JSONObject response-----------");
                Logs(response.toString());
                // If the response is JSONObject instead of expected JSONArray

                HttpModel httpModel = new HttpModel(HttpModel.State.Success);
                httpModel.setJsonObject(response);

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Logs("onSuccess()  Call JSONArray jsonArray");
                // Pull out the first event on the public timeline

                HttpModel httpModel = new HttpModel(HttpModel.State.Success);
                httpModel.setMessage("Error");
                httpModel.setJSONArray(jsonArray);

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                //Some debugging code here, show retry dialog, feedback etc.
                Logs("onFailure()  Call");
                Logs("statusCode: " + statusCode);
                Logs("headers: " + headers);
                //Logs("errorResponse: " + errorResponse.toString());

                HttpModel httpModel = new HttpModel(HttpModel.State.Failure);
                httpModel.setMessage("Error");
                httpModel.setData("Null");
                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Logs("onFailure JSONArray");

                HttpModel httpModel = new HttpModel(HttpModel.State.Failure);
                httpModel.setMessage("Error");
                httpModel.setData("Null");
                httpModel.setJSONArray(errorResponse);

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Logs("onFailure String");
                Logs("onFailure statusCode: " + statusCode);
                Logs("onFailure []headers: " + headers.toString());
                Logs("onFailure responseString: " + responseString);
                Logs("onFailure throwable: " + throwable.getMessage());

                HttpModel httpModel = new HttpModel(HttpModel.State.Failure);
                httpModel.setData(responseString);
                httpModel.setMessage("Error");

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Logs("onSuccess String");
                HttpModel httpModel = new HttpModel(HttpModel.State.Success);
                httpModel.setMessage("Error");

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onStart() {
                // called before request is started
                Logs("onStart()  Call");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Logs("onRetry()  Call");
            }
        };

        return responseHandler;
    }

    private FileAsyncHttpResponseHandler getFileAsyncHttpResponseHandler(final IHttpCallback callback, Context context) {
        FileAsyncHttpResponseHandler fileAsyncHttpResponseHandler = new FileAsyncHttpResponseHandler(context) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                //Some debugging code here, show retry dialog, feedback etc.
                Logs("onFailure()  Call");
                Logs("statusCode: " + statusCode);
                Logs("headers: " + headers);
                //Logs("errorResponse: " + errorResponse.toString());

                HttpModel httpModel = new HttpModel(HttpModel.State.Failure);
                httpModel.setFile(file);
                httpModel.setMessage("Error");
                httpModel.setData("Null");

                postCallback(statusCode, headers, httpModel, callback);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                HttpModel httpModel = new HttpModel(HttpModel.State.Success);
                httpModel.setFile(file);
                postCallback(statusCode, headers, httpModel, callback);
            }
        };

        return fileAsyncHttpResponseHandler;
    }

    private void postCallback(int statusCode, Header[] headers, HttpModel httpModel, IHttpCallback callback) {
        httpModel.setHeader(headers);
        httpModel.setStatus(statusCode);
        //httpModel.setState(state);

        if (callback != null) {
            callback.onHttpData(httpModel);
        }
    }

    private AsyncHttpResponseHandler responseHandler3(final IHttpCallback callback) {

        AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Logs("onSuccess(int statusCode, Header[] headers, JSONObject response)");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Logs("onSuccess(int statusCode, Header[] headers, JSONArray jsonArray)");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Logs("onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Logs("onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Logs("onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Logs("onSuccess(int statusCode, Header[] headers, String responseString)");
            }

            @Override
            public void onStart() {
                // called before request is started
                Logs("onStart()  Call");
            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Logs("onRetry()  Call");
            }
        };

        return responseHandler;
    }

    public void Logs(String s) {
        ShowLog.e(TAG, s);
    }
}