package com.nurdcoder.android.icr_wallet.data.local.user;

import android.arch.lifecycle.LiveData;

import com.nurdcoder.android.util.helper.ShowLog;

import java.util.List;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : Ahmed Mohmmad Ullah (Azim)
 * * Date : 2/15/18
 * * Email : azim@w3engineers.com
 * *
 * * Purpose: Using the Room database as a data source.
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 12/21/17.
 * * History: Comment Added
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 3/19/18.
 * ****************************************************************************
 */

public class UserService {
    private final UserDao mUserDao;

    public UserService(UserDao userDao) {
        mUserDao = userDao;
    }

    public UserEntity getUser(String meshID) {
        return mUserDao.getUser(meshID);
    }

    public UserEntity getUserById(long id) {
        return mUserDao.getUserById(id);
    }

    public long insert(UserEntity userEntity) {
        long id = mUserDao.insert(userEntity);
        userEntity.setId(id);
        return id;
    }

    public int updateUser(UserEntity userEntity) {
        int value = mUserDao.update(userEntity);
        ShowLog.d("Updated value", value + "");
        return value;
    }

    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }

    public void deleteItem(UserEntity userEntity) {
        mUserDao.delete(userEntity);
    }

    public List<UserEntity> getAllUser() {
        return mUserDao.getAllUser();
    }

    public LiveData<List<UserEntity>> getAllUserLiveData() {
        return mUserDao.getAllUserLiveData();
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getAllUserLiveData();
    }
}
