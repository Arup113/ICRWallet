package com.nurdcoder.android.icr_wallet.ui.email_verification;

import com.nurdcoder.android.icr_wallet.ui.base.MvpView;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/3/2018 at 12:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: View class
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 8/3/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface EmailVerificationMVPView extends MvpView {

    void onCaptchaVerified(boolean isSuccess, String message);

    void onPasswordResetRequest(boolean success, String message);
}
