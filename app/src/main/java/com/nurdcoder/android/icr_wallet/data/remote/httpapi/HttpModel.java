package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : SUDIPTA KUMAR PAIK
* * Date : 2/15/18
* * Email : sudipta@w3engineers.com
* *
* * Purpose:
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 2/15/18.
* * History:
* * 1:
* * 2:
* *  
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/15/18.
* ****************************************************************************
*/

public class HttpModel {
    private int status;
    private Header[] header;
    private JSONObject jsonObject;
    private File file;
    private String message;

    public String getData() {
        return data;
    }

    private String data;
    private org.json.JSONArray JSONArray;
    private State state;

    public HttpModel() {
    }

    public HttpModel(File file) {
        this.file = file;
    }

    public HttpModel(String responseString) {
        this.data = responseString;
    }

    public HttpModel(State state) {
        this.state = state;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setHeader(Header[] header) {
        this.header = header;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setJSONArray(org.json.JSONArray JSONArray) {
        this.JSONArray = JSONArray;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        Success, Failure
    }
}
