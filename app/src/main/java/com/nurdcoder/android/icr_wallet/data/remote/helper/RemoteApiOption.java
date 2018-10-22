package com.nurdcoder.android.icr_wallet.data.remote.helper;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [23-Jul-2018 at 10:48 AM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [23-Jul-2018 at 10:48 AM].
 * * --> <Second Editor> on [23-Jul-2018 at 10:48 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [23-Jul-2018 at 10:48 AM].
 * * --> <Second Reviewer> on [23-Jul-2018 at 10:48 AM].
 * * ============================================================================
 **/

public class RemoteApiOption {
    private boolean mIsStartTCharm;
    private boolean mIsStartS3;
    private boolean mIsStartFireBase;
    private boolean mIsStartParse;
    private boolean mIsStartRightMesh;
    private boolean mIsStartHttp;
    private boolean mIsStartRetrofit;

    public RemoteApiOption(boolean isStartAll) {
 /*mIsStartTCharm = isStartAll;
 mIsStartS3 = isStartAll;
 mIsStartFireBase = isStartAll;
 mIsStartParse = isStartAll;
 mIsStartRightMesh = isStartAll;
 mIsStartHttp = isStartAll;
 */

        mIsStartTCharm = false;
        mIsStartS3 = false;
        mIsStartFireBase = false;
        mIsStartParse = isStartAll;
        mIsStartRightMesh = false;
        mIsStartHttp = false;
        mIsStartRetrofit = isStartAll;
    }

    public boolean isStartHttp() {
        return mIsStartHttp;
    }

    public void setStartHttp(boolean startHttp) {
        mIsStartHttp = startHttp;
    }

    public boolean isStartTCharm() {
        return mIsStartTCharm;
    }

    public void setStartTCharm(boolean startTCharm) {
        mIsStartTCharm = startTCharm;
    }

    public boolean isStartS3() {
        return mIsStartS3;
    }

    public void setStartS3(boolean startS3) {
        mIsStartS3 = startS3;
    }

    public boolean isStartFireBase() {
        return mIsStartFireBase;
    }

    public void setStartFireBase(boolean startFireBase) {
        mIsStartFireBase = startFireBase;
    }

    public boolean isStartParse() {
        return mIsStartParse;
    }

    public void setStartParse(boolean startParse) {
        mIsStartParse = startParse;
    }

    public boolean isStartRightMesh() {
        return mIsStartRightMesh;
    }

    public void setStartRightMesh(boolean startRightMesh) {
        mIsStartRightMesh = startRightMesh;
    }

    public boolean ismIsStartRetrofit() {
        return mIsStartRetrofit;
    }

    public void setmIsStartRetrofit(boolean mIsStartRetrofit) {
        this.mIsStartRetrofit = mIsStartRetrofit;
    }

}