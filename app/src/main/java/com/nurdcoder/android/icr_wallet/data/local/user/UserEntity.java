/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

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
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
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
    private String mFullName;

    public UserEntity(String meshID, String userName) {
        mMeshID = meshID;
        mUserName = userName;
    }

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
}