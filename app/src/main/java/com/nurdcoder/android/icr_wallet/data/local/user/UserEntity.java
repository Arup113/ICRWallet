package com.nurdcoder.android.icr_wallet.data.local.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.nurdcoder.android.icr_wallet.data.local.base.RoomEntity;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.TableNames;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : Ahmed Mohmmad Ullah (Azim)
 * * Date : 2/15/18
 * * Email : azim@w3engineers.com
 * *
 * * Purpose: Sample of ROOM model
 * *
 * * Last Edited by : Mohd. Asfaq-E-Azam on 12/21/17.
 * * History: Comment Added
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 3/19/18.
 * ****************************************************************************
 */

@Entity(tableName = TableNames.USERS, indices = {@Index(value = {ColumnNames.USER_ID}, unique = true)})
public class UserEntity extends RoomEntity {
    @ColumnInfo(name = ColumnNames.USER_ID)
    @NonNull
    public String mMeshID;
    @Bindable
    @ColumnInfo(name = ColumnNames.USER_NAME)
    public String mUserName;
    @Bindable
    @ColumnInfo(name = ColumnNames.USER_PASSWORD)
    public String mPassword;
    @Bindable
    @ColumnInfo(name = ColumnNames.USER_EMAIL)
    public String mEmail;
    private String mPhoneNumber;
    private boolean isSuccess;

    public String getFullName() {
        return mFullName;
    }

    private String mFullName;

    public UserEntity(String meshID, String userName) {
        mMeshID = meshID;
        mUserName = userName;
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

    @NonNull
    public String getMeshID() {
        return mMeshID;
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

    public void setFullName(String fullName) {
        mFullName = fullName;
    }
}