package com.nurdcoder.android.icr_wallet.data.remote.helper.callback;

import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [17-Jul-2018 at 11:21 AM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [17-Jul-2018 at 11:21 AM].
 * * --> <Second Editor> on [17-Jul-2018 at 11:21 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * --> <Second Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * ============================================================================
 **/

public interface AuthSignInCallback {
    void onSignIn(UserModel userModel, boolean isSuccess, String message);
}