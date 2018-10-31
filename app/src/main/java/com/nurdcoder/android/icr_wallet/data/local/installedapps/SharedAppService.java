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
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.DatabaseService;

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

public class SharedAppService {

    //    private SharedAppsDao sharedAppsDao;
    private static MediatorLiveData<List<SharedAppsEntity>> mObservableProducts = new MediatorLiveData<>();

//    public SharedAppService(SharedAppsDao sharedAppsDao) {
//        this.sharedAppsDao = sharedAppsDao;
//        mObservableProducts = new MediatorLiveData<>();
//    }

    public static long insert(SharedAppsEntity sharedAppsEntity) {
        return DatabaseService.on().sharedAppsDao().insert(sharedAppsEntity);
    }

    public static void update(SharedAppsEntity sharedAppsEntity) {
        DatabaseService.on().sharedAppsDao().update(sharedAppsEntity);
    }

    public static void remove(SharedAppsEntity sharedAppsEntity) {
        DatabaseService.on().sharedAppsDao().delete(sharedAppsEntity);
    }


    /**
     * Get list of installed apps entity in value is in specific column
     *
     * @param columnName column in db
     * @param value      query to be searched
     * @return list of installed apps entity
     */
    public static List<SharedAppsEntity> getValueWhereIn(String columnName, String value) {
        if (columnName.equals(ColumnNames.APP_NAME)) {
            return DatabaseService.on().sharedAppsDao().getAllAppsByAppNameWhereIn(value);
        } else if (columnName.equals(ColumnNames.TAGS)) {
            return DatabaseService.on().sharedAppsDao().getAllAppsByTagsNameWhereIn(value);
        } else if (columnName.equals(ColumnNames.PACKAGE_NAME)) {
            return DatabaseService.on().sharedAppsDao().getAllAppsByPackageNameWhereIn(value);
        } else {
            return DatabaseService.on().sharedAppsDao().getAllAppsBySDNameWhereIn(value);
        }
    }

    public static List<SharedAppsEntity> getMeshSharedApps() {
        return DatabaseService.on().sharedAppsDao().getMeshSharedApps();
    }

    public LiveData<List<SharedAppsEntity>> getAllApps() {

        mObservableProducts.addSource(DatabaseService.on().sharedAppsDao().getAllApps(), new Observer<List<SharedAppsEntity>>() {
            @Override
            public void onChanged(@Nullable List<SharedAppsEntity> installedAppsEntities) {
                mObservableProducts.postValue(installedAppsEntities);
            }
        });

        return mObservableProducts;
    }

}
