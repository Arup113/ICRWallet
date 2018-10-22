package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

import android.content.Context;

import com.loopj.android.http.RequestParams;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : SUDIPTA KUMAR PAIK
* * Date : 2/14/18
* * Email : sudipta@w3engineers.com
* *
* * Purpose: HTTP Request sample code
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 2/14/18.
* * History:
* * 1:
* * 2:
* *
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/14/18.
* ****************************************************************************
*/

public class HttpManager {
     private static volatile HttpManager ourInstance;
    private static Object mutex = new Object();
    String TAG = "AsyncHandler skpaik";
    private String URL_user_info = "user/add";
    private String URL_user_list = "user/list";

    private HttpManager() {
    }

    public synchronized static void init(Context context) {
        synchronized (mutex) {
            if (ourInstance == null)
                ourInstance = new HttpManager();
        }
    }

    public static HttpManager on() {
        return ourInstance;
    }

    public void getUserList(RequestParams params, IHttpCallback iHttpCallback) {
        new HttpAPI().getData(URL_user_list, params, iHttpCallback);
    }

    public void postUserData(RequestParams params, IHttpCallback iHttpCallback) {
        new HttpAPI().postData(URL_user_info, params, iHttpCallback);
    }
}