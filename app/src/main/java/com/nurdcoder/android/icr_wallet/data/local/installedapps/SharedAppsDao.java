package com.nurdcoder.android.icr_wallet.data.local.installedapps;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import com.nurdcoder.android.icr_wallet.data.local.base.BaseDao;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.TableNames;


/**
 * Created by: Mithun Sarker on 7/30/18 at 3:18 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Dao class for {@link SharedAppsEntity}
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
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
