package com.nurdcoder.android.icr_wallet.ui.sign_up;

import com.nurdcoder.android.icr_wallet.ui.base.MvpView;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/1/2018 at 1:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: View class
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 8/1/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface SignUpMvpView extends MvpView {

    void onSignUp(boolean isSuccess, String message);

    void isValidInput(boolean isValid, String message);
}
