package com.nurdcoder.android.icr_wallet.data.helper;

import android.content.Context;
import android.os.AsyncTask;

import com.facebook.stetho.Stetho;
import com.nurdcoder.android.icr_wallet.BuildConfig;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.DatabaseService;
import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.RemoteApiOption;
import com.nurdcoder.android.util.helper.Toaster;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [13-Jul-2018 at 4:18 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> Rezwanur on [05-Oct-2018 at 11:11 AM].
 * * --> <Second Editor> on [13-Jul-2018 at 4:18 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [13-Jul-2018 at 4:18 PM].
 * * --> <Second Reviewer> on [13-Jul-2018 at 4:18 PM].
 * * ============================================================================
 **/

public class StartApp {
    private static Context sContext;

    public synchronized void init(Context context) {
        sContext = context;


    }


}
