package com.nurdcoder.android.util.helper;

import android.util.Log;

import com.nurdcoder.android.icr_wallet.BuildConfig;


/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 23/5/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : Helper class for efficient Logging, where logs are automatically disappear in release mood.
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 23/5/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 8/8/18.
 * ****************************************************************************
 */

public class ShowLog {

    public static void i(String tag, String string) {
        if (BuildConfig.DEBUG) Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (BuildConfig.DEBUG) Log.e(tag, string);
    }

    public static void d(String tag, String string) {
        if (BuildConfig.DEBUG) Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (BuildConfig.DEBUG) Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (BuildConfig.DEBUG) Log.w(tag, string);
    }
}