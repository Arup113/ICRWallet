package com.nurdcoder.android.icr_wallet.data.local.installedapps;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.DatabaseService;


/**
 * Created by: Mithun Sarker on 7/30/18 at 3:19 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Using the Room database as a data source.
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
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

    public LiveData<List<SharedAppsEntity>> getAllApps() {

        mObservableProducts.addSource(DatabaseService.on().sharedAppsDao().getAllApps(), new Observer<List<SharedAppsEntity>>() {
            @Override
            public void onChanged(@Nullable List<SharedAppsEntity> installedAppsEntities) {
                mObservableProducts.postValue(installedAppsEntities);
            }
        });

        return mObservableProducts;
    }


    public static List<SharedAppsEntity> getMeshSharedApps() {
        return DatabaseService.on().sharedAppsDao().getMeshSharedApps();
    }

}
