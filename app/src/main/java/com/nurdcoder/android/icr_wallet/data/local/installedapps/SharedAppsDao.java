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

package com.nurdcoder.android.icr_wallet.data.local.installedapps;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.nurdcoder.android.icr_wallet.data.local.base.BaseDao;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.TableNames;

import java.util.List;

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

@Dao
public interface SharedAppsDao extends BaseDao<SharedAppsEntity> {


    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS)
    LiveData<List<SharedAppsEntity>> getAllApps();


//    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.USER_ID + " = :meshID")
//    UserEntity getUser(String meshID);

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.APP_NAME + " LIKE + '%' || :value || '%'")
    List<SharedAppsEntity> getAllAppsByAppNameWhereIn(String value);

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.TAGS + " LIKE + '%' || :value || '%'")
    List<SharedAppsEntity> getAllAppsByTagsNameWhereIn(String value);

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.PACKAGE_NAME + " LIKE + '%' || :value || '%'")
    List<SharedAppsEntity> getAllAppsByPackageNameWhereIn(String value);

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.SHORT_DESCRIPTION_WITHOUT_PREPOSITION + " LIKE + '%' || :value || '%'")
    List<SharedAppsEntity> getAllAppsBySDNameWhereIn(String value);

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS)
    List<SharedAppsEntity> getMeshSharedApps();

    @Query("SELECT COUNT(" + ColumnNames.IS_SHARED_ON_MESH + ") FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.IS_SHARED_ON_MESH + "= 1")
    int getNumberOfRows();

    @Query("SELECT * FROM " + TableNames.MESH_SHARED_APPS + " WHERE " + ColumnNames.PACKAGE_NAME + "=:name")
    List<SharedAppsEntity> getAppBypackageName(String name);

}
