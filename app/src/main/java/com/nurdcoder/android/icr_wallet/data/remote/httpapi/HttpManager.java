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

package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

import android.content.Context;

import com.loopj.android.http.RequestParams;

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