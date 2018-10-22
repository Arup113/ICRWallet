package com.nurdcoder.android.icr_wallet.data.remote.helper.models;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [19-Jul-2018 at 4:24 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [19-Jul-2018 at 4:24 PM].
 * * --> <Second Editor> on [19-Jul-2018 at 4:24 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [19-Jul-2018 at 4:24 PM].
 * * --> <Second Reviewer> on [19-Jul-2018 at 4:24 PM].
 * * ============================================================================
 **/


import android.net.Uri;

public class UserModel {
    public String mUserName;

    public String mPassword;

    public String mEmail;
    private String mPhoneNumber;
    private boolean isSuccess;
    private String mFullName;
    private Uri mPhotoUrl;
    private boolean mEmailVerified;
    private String mUid;

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setPhotoUrl(Uri photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public void setEmailVerified(boolean emailVerified) {
        mEmailVerified = emailVerified;
    }

    public boolean getEmailVarified() {
        return mEmailVerified;
    }

    public void setUid(String uid) {
        mUid = uid;
    }
}