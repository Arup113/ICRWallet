package com.nurdcoder.android.icr_wallet.data.remote.helper.callback;

import java.util.HashMap;

/**
 * Created by: Mithun Sarker on 8/29/18 at 5:54 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Callback when getting an single object as a response
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface ResponseCallBack {
    <T> void onResponseReceived(Object ts, Class<T> expectedClass, HashMap<String, Object> paramsUsed, boolean isSuccess, String message);
}
