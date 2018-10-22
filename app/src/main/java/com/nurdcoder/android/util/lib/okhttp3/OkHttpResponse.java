package com.nurdcoder.android.util.lib.okhttp3;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : SUDIPTA KUMAR PAIK
* * Date : 2/12/18
* * Email : sudipta@w3engineers.com
* *
* * Purpose: Main class to interact with library. Please don't change without discussion with CTO
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 2/12/18.
* * History:
* * 1:
* * 2:
* * Source: http://square.github.io/okhttp/
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 03/08/18.
* ****************************************************************************
*/

public interface OkHttpResponse {
    void onSuccess(String postUrl, String responseData);
    void onFail(String postUrl);
}
