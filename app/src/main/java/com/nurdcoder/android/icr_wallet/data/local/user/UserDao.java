package com.nurdcoder.android.icr_wallet.data.local.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import com.nurdcoder.android.icr_wallet.data.local.base.BaseDao;
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
 * * Purpose: Data Access Object for the users table.
 * *
 * * Last Edited by : Ahmed Mohmmad Ullah (Azim) on 12/21/17.
 * * History: Comment Added
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 3/19/18.
 * ****************************************************************************
 */

@Dao
public interface UserDao extends BaseDao<UserEntity> {
    @Query("SELECT * FROM " + TableNames.USERS)
    List<UserEntity> getAllUser();

    @Query("SELECT * FROM " + TableNames.USERS)
    LiveData<List<UserEntity>> getAllUserLiveData();

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.USER_ID + " = :meshID")
    UserEntity getUser(String meshID);

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.ID + " = :id")
    UserEntity getUserById(long id);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM " + TableNames.USERS)
    void deleteAllUsers();
}