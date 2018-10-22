package com.nurdcoder.android.icr_wallet.ui.sign_in;

import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;
import com.nurdcoder.android.icr_wallet.ui.base.MvpView;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 7/31/2018 at 11:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: View class
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 7/31/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface SignInMvpView extends MvpView {
    void onSignIn(boolean isSuccess, String message);

    void isValidUserInput(boolean isValid, String message);

    void onUserVerified(boolean isVerified, String message);
}
