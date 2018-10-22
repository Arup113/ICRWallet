package com.nurdcoder.android.icr_wallet.data.local.download_task;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import com.nurdcoder.android.icr_wallet.data.local.base.BaseDao;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : MD. REZWANUR RAHMAN KHAN
 * * Date : 10/04/18
 * * Email : rezwanur@w3engineers.com
 * *
 * * Purpose : Dao class for downloads queue
 * *
 * * Last Edited by : Rezwanur on 10/09/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : Rezwanur on 10/09/18.
 * ****************************************************************************
 */

@Dao
public interface DownloadTaskDao extends BaseDao<DownloadTaskEntity> {

    @Query("SELECT * FROM DOWNLOADS_QUEUE")
    List<DownloadTaskEntity> getDownloadsQueue();

    @Query("SELECT * FROM DOWNLOADS_QUEUE")
    LiveData<List<DownloadTaskEntity>> getDownloadsQueueLiveData();

    @Query("SELECT COUNT(id) FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    int isAlreadyInQueue(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    LiveData<DownloadTaskEntity> getDownloadInfoByAppIdLiveData(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    DownloadTaskEntity getDownloadInfoByAppId(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE downloadId = :downloadId")
    DownloadTaskEntity getDownloadInfoByDownloadId(long downloadId);

    @Query("DELETE FROM DOWNLOADS_QUEUE WHERE downloadId = :downloadId")
    void removeAppFromDownloadsQueueByDownloadId(long downloadId);
}