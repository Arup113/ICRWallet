package com.nurdcoder.android.icr_wallet.data.remote.helper.callback;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Md. Rezwanur Rahman Khan on [15-Oct-2018 at 11:52 AM].
 * * Email: rezwanur@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Mesh App Store
 * * Code Responsibility: Callback class for user profile update.
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> Rezwanur on [15-Oct-2018 at 11:52 AM].
 * * --> <Second Editor> on [17-Jul-2018 at 11:21 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * --> <Second Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * ============================================================================
 **/

public interface ProfileUpdateCallback {
    void onProfileUpdated(boolean isSuccess, String message);
}