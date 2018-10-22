package com.nurdcoder.android.icr_wallet.data.remote.helper;

import android.content.Context;
import android.os.AsyncTask;

import com.nurdcoder.android.icr_wallet.BuildConfig;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.remote.httpapi.HttpManager;
import com.nurdcoder.android.icr_wallet.data.remote.parseapi.ParseManager;
import com.nurdcoder.android.icr_wallet.data.remote.retrofit.RetrofitManager;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [19-Jul-2018 at 7:01 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [19-Jul-2018 at 7:01 PM].
 * * --> <Second Editor> on [19-Jul-2018 at 7:01 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [19-Jul-2018 at 7:01 PM].
 * * --> <Second Reviewer> on [19-Jul-2018 at 7:01 PM].
 * * ============================================================================
 **/

public class InitManagerAsync extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private RemoteApiOption mRemoteApiOption;

    public InitManagerAsync(Context context, RemoteApiOption remoteApiOption) {
        mContext = context;
        mRemoteApiOption = remoteApiOption;

        if (mRemoteApiOption == null)
            mRemoteApiOption = new RemoteApiOption(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (mRemoteApiOption.isStartTCharm()) {
//            TCharmManager.init(mContext);
        }
        if (mRemoteApiOption.isStartS3()) {
//            S3Manager.init(mContext);
        }
        if (mRemoteApiOption.isStartFireBase()) {
//            FireBaseManager.init(mContext);
        }
        if (mRemoteApiOption.isStartParse()) {
            ParseManagerInit();
        }
        if (mRemoteApiOption.isStartRightMesh()) {
//            RMManager.on().init(mContext);
        }
        if (mRemoteApiOption.isStartHttp()) {
            HttpManager.init(mContext);
        }
        if (mRemoteApiOption.ismIsStartRetrofit()) {
            RetrofitManager.init(mContext);
        }

        return null;
    }

    private void ParseManagerInit() {
        String serverUrl = BuildConfig.DEBUG ? mContext.getString(R.string.parse_development_url) : mContext.getString(R.string.parse_production_url);
        String appId = mContext.getString(R.string.parse_app_id);
        String clientKey = mContext.getString(R.string.parse_client_key);

        ParseManager.init(mContext, serverUrl, appId, clientKey);
    }
}