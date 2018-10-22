package com.nurdcoder.android.icr_wallet.data.local.dbstorage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.local.installedapps.SharedAppsDao;
import com.nurdcoder.android.icr_wallet.data.local.installedapps.SharedAppsEntity;
import com.nurdcoder.android.icr_wallet.data.local.download_task.DownloadTaskDao;
import com.nurdcoder.android.icr_wallet.data.local.download_task.DownloadTaskEntity;
import com.nurdcoder.android.icr_wallet.data.local.user.UserDao;
import com.nurdcoder.android.icr_wallet.data.local.user.UserEntity;
import com.nurdcoder.android.util.helper.Converter;
import com.nurdcoder.android.util.lib.roomdb.AppDatabase;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : Ahmed Mohmmad Ullah (Azim)
* * Date : 2/15/18
* * Email : azim@w3engineers.com
* *
* * Purpose: ROOM DB Services start class
* *
* * Last Edited by : SUDIPTA KUMAR PAIK on 12/21/17.
* * History: Comment Added
* * 1:
* * 2:
* *
* * Last Reviewed by : SUDIPTA KUMAR PAIK on 3/19/18.
* ****************************************************************************
*/

@Database(entities = {
        UserEntity.class,
        DownloadTaskEntity.class,
        SharedAppsEntity.class},
        version = 3, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class DatabaseService extends AppDatabase {

    private static final String MIGRATION_SQL_1_2 = "ALTER TABLE  Employee ADD COLUMN address TEXT";
    /*
    public static AppDatabaseService on(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseService.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabaseService.class, context.getString(R.string.app_name))
                            .build();
                }
            }
        }

        return INSTANCE;
    }
    */
    private static volatile DatabaseService sInstance;
    private static volatile DatabaseService INSTANCE;

    // Create the database
    private static DatabaseService createOld(Context context) {
        RoomDatabase.Builder<DatabaseService> builder =
                Room.databaseBuilder(context, DatabaseService.class, context.getString(R.string.app_name))
                        .allowMainThreadQueries();

        return builder.build();
    }

    // Get a database instance
    public static synchronized DatabaseService on() {
        return sInstance;
    }

    public static synchronized DatabaseService init(Context context) {

        if (sInstance == null) {
            synchronized (DatabaseService.class) {
                sInstance = createDb(context, context.getString(R.string.app_name), DatabaseService.class
                        , MIGRATION_SQL_1_2);
            }
        }

        return sInstance;
    }

    public abstract UserDao userDao();

    public abstract DownloadTaskDao downloadTaskDao();

    public abstract SharedAppsDao sharedAppsDao();

    @Override
    public void clearAllTables() {

    }
}