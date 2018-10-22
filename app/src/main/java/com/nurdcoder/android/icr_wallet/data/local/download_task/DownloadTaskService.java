package com.nurdcoder.android.icr_wallet.data.local.download_task;

import android.arch.lifecycle.LiveData;

import java.util.List;

import com.nurdcoder.android.icr_wallet.data.local.dbstorage.DatabaseService;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : MD. REZWANUR RAHMAN KHAN
 * * Date : 10/04/18
 * * Email : rezwanur@w3engineers.com
 * *
 * * Purpose : Download queue db helper class
 * *
 * * Last Edited by : Rezwanur on 10/10/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : Rezwanur on 10/10/18.
 * ****************************************************************************
 */

public class DownloadTaskService {

    public static long addAppToDownloadsQueue(DownloadTaskEntity downloadTaskEntity) {
        return DatabaseService.on().downloadTaskDao().insert(downloadTaskEntity);
    }

    // Required later
    public static void removeAppFromDownloadsQueueByDownloadId(long downloadId) {
        DatabaseService.on().downloadTaskDao().removeAppFromDownloadsQueueByDownloadId(downloadId);
    }

    public static void removeAppFromDownloadsQueue(DownloadTaskEntity downloadTaskEntity) {
        DatabaseService.on().downloadTaskDao().delete(downloadTaskEntity);
    }

    public static int updateAppDownloadInfo(DownloadTaskEntity downloadTaskEntity) {
        return DatabaseService.on().downloadTaskDao().update(downloadTaskEntity);
    }

    public static LiveData<List<DownloadTaskEntity>> getDownloadsQueueLiveData() {
        return DatabaseService.on().downloadTaskDao().getDownloadsQueueLiveData();
    }

    public static List<DownloadTaskEntity> getDownloadsQueue() {
        return DatabaseService.on().downloadTaskDao().getDownloadsQueue();
    }

    // Required later
    public static boolean isAlreadyInQueue(String appId) {
        return DatabaseService.on().downloadTaskDao().isAlreadyInQueue(appId) > 0;
    }

    public static LiveData<DownloadTaskEntity> getDownloadInfoByAppIdLiveData(String appId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByAppIdLiveData(appId);
    }

    public static DownloadTaskEntity getDownloadInfoByAppId(String appId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByAppId(appId);
    }

    public static DownloadTaskEntity getDownloadInfoByDownloadId(long downloadId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByDownloadId(downloadId);
    }
}
