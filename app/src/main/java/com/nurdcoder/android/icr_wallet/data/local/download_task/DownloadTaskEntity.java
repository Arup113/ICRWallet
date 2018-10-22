package com.nurdcoder.android.icr_wallet.data.local.download_task;

import android.arch.persistence.room.Entity;

import com.nurdcoder.android.icr_wallet.data.local.base.RoomEntity;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.TableNames;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : MD. REZWANUR RAHMAN KHAN
 * * Date : 10/04/18
 * * Email : rezwanur@w3engineers.com
 * *
 * * Purpose : Entity class for downloads queue
 * *
 * * Last Edited by : Rezwanur on 10/08/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : Rezwanur on 10/08/18.
 * ****************************************************************************
 */

@Entity(tableName = TableNames.DOWNLOADS_QUEUE)
public class DownloadTaskEntity extends RoomEntity {

    String appId;
    String appName;
    String appUrl;
    String appData;
    boolean isDownloading;
    long downloadId;

    public DownloadTaskEntity(String appId, String appName, String appUrl, String appData) {
        this.appId = appId;
        this.appName = appName;
        this.appUrl = appUrl;
        this.appData = appData;
    }

    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void setDownloading(boolean downloading) {
        isDownloading = downloading;
    }

}
