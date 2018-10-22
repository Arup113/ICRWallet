package com.nurdcoder.android.icr_wallet.data.local.installedapps;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

/**
 * Created by: Mithun Sarker on 8/2/18 at 3:46 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Though we already have an Entity {@link SharedAppsEntity} , it is a shorter version only for transferring via Mesh
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class SharedAppsShortModel {


    String appName;
    String author;
    String packageName;
    String logo;   //Base64 Image
    String deviceName;
    long apkSize;
    String shredByMeshId;

    public SharedAppsShortModel() {

    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getShredByMeshId() {
        return shredByMeshId;
    }

    public void setShredByMeshId(String shredByMeshId) {
        this.shredByMeshId = shredByMeshId;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedAppsShortModel that = (SharedAppsShortModel) o;
        return Objects.equals(appName, that.appName) &&
                Objects.equals(author, that.author) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(logo, that.logo) &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(apkSize, that.apkSize) &&
                Objects.equals(shredByMeshId, that.shredByMeshId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(appName, author, packageName, logo, deviceName, apkSize, shredByMeshId);
    }
}
