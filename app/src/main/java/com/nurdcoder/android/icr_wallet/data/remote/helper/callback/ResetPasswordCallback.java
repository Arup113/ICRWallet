package com.nurdcoder.android.icr_wallet.data.remote.helper.callback;

/**
 * Created by: Mithun Sarker on 8/7/18 at 1:05 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: <Purpose of code>
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface ResetPasswordCallback {
    void onResetPasswordRequest(boolean isSuccess, String message);
}
