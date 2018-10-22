package com.nurdcoder.android.icr_wallet.data.remote.httpapi;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : SUDIPTA KUMAR PAIK
* * Date : 2/14/18
* * Email : sudipta@w3engineers.com
* *
* * Purpose: Interface for remote api callback
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 2/14/18.
* * History:
* * 1:
* * 2:
* *
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/14/18.
* ****************************************************************************
*/
public interface IHttpCallback {
    void onHttpData(HttpModel httpModel);
}