package com.nurdcoder.android.icr_wallet.data.local.installedapps;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.os.Parcelable;

import com.nurdcoder.android.icr_wallet.data.local.base.RoomEntity;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.TableNames;

/**
 * Created by: Mithun Sarker on 7/30/18 at 3:16 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Entity class for Shared and mesh store apps
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
@Entity(tableName = TableNames.MESH_SHARED_APPS, indices = {@Index(value = {ColumnNames.PACKAGE_NAME}, unique = true)})
public class SharedAppsEntity extends RoomEntity implements Parcelable {


    @ColumnInfo(name = ColumnNames.APP_NAME)
    private String appName;
    @ColumnInfo(name = ColumnNames.APPLICATION_ID)
    private String applicationId;
    @ColumnInfo(name = ColumnNames.SHORT_DESCRIPTION)
    private String shortDescription;
    @ColumnInfo(name = ColumnNames.SHORT_DESCRIPTION_WITHOUT_PREPOSITION)
    private String shortDescriptionWithoutPreposition;
    @ColumnInfo(name = ColumnNames.DETAILED_DESCRIPTION)
    private String detailedDescription;
    @ColumnInfo(name = ColumnNames.LOGO_URL)
    private String logoUrl;
    @ColumnInfo(name = ColumnNames.LOGO_IMAGE)
    private String logoImage; // Base64String
    @ColumnInfo(name = ColumnNames.CONTENT_RATING)
    private String contentRating;
    @ColumnInfo(name = ColumnNames.INSTALLED_COUNT)
    private int installCount;
    @ColumnInfo(name = ColumnNames.STORE_NAME)
    private String storeName;
    @ColumnInfo(name = ColumnNames.STORE_ID)
    private String storeId;
    @ColumnInfo(name = ColumnNames.STORE_LOGO_URL)
    private String storeLogoUrl;
    @ColumnInfo(name = ColumnNames.STORE_LOGO)
    private String storeLogo;  //Base64String
    @ColumnInfo(name = ColumnNames.PACKAGE_NAME)
    private String packagaeName;
    @ColumnInfo(name = ColumnNames.TAGS)
    private String tags;
    @ColumnInfo(name = ColumnNames.REQUESTED_VIA_MESH)
    private int requestedViaMesh;  // search counter, might be required for sorting search result
    @ColumnInfo(name = ColumnNames.IS_SHARED_ON_MESH)
    private boolean isSharedOnMesh;
    @ColumnInfo(name = ColumnNames.IS_SYNCED)
    private boolean isSynced; // is all data  up to date with internet
    @ColumnInfo(name = ColumnNames.IS_RM_APPS)
    private boolean isRmApps; // is it icr_wallet apps
    @ColumnInfo(name = ColumnNames.APK_SIZE)
    private long apkSize; // is it icr_wallet apps

    public SharedAppsEntity() {

    }


    public static final Parcelable.Creator<SharedAppsEntity> CREATOR = new Parcelable.Creator<SharedAppsEntity>() {
        @Override
        public SharedAppsEntity createFromParcel(Parcel source) {
            return new SharedAppsEntity(source);
        }

        @Override
        public SharedAppsEntity[] newArray(int size) {
            return new SharedAppsEntity[size];
        }
    };

    protected SharedAppsEntity(Parcel in) {
        this.appName = in.readString();
        this.applicationId = in.readString();
        this.shortDescription = in.readString();
        this.detailedDescription = in.readString();
        this.logoUrl = in.readString();
        this.logoImage = in.readString();
        this.contentRating = in.readString();
        this.installCount = in.readInt();
        this.storeName = in.readString();
        this.storeId = in.readString();
        this.storeLogoUrl = in.readString();
        this.storeLogo = in.readString();
        this.packagaeName = in.readString();
        this.tags = in.readString();
        this.requestedViaMesh = in.readInt();
        this.isSharedOnMesh = in.readByte() != 0;
        this.isSynced = in.readByte() != 0;
        this.isRmApps = in.readByte() != 0;
        this.apkSize = in.readLong();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescriptionWithoutPreposition() {
        return shortDescriptionWithoutPreposition;
    }

    public void setShortDescriptionWithoutPreposition(String text) {
        this.shortDescriptionWithoutPreposition = text;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public int getInstallCount() {
        return installCount;
    }

    public void setInstallCount(int installCount) {
        this.installCount = installCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreLogoUrl() {
        return storeLogoUrl;
    }

    public void setStoreLogoUrl(String storeLogoUrl) {
        this.storeLogoUrl = storeLogoUrl;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getPackagaeName() {
        return packagaeName;
    }

    public void setPackagaeName(String packagaeName) {
        this.packagaeName = packagaeName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getRequestedViaMesh() {
        return requestedViaMesh;
    }

    public void setRequestedViaMesh(int requestedViaMesh) {
        this.requestedViaMesh = requestedViaMesh;
    }

    public boolean isSharedOnMesh() {
        return isSharedOnMesh;
    }

    public void setSharedOnMesh(boolean sharedOnMesh) {
        isSharedOnMesh = sharedOnMesh;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public boolean isRmApps() {
        return isRmApps;
    }

    public void setRmApps(boolean rmApps) {
        isRmApps = rmApps;
    }

    public long getApkSize() {
        return this.apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.applicationId);
        dest.writeString(this.shortDescription);
        dest.writeString(this.detailedDescription);
        dest.writeString(this.logoUrl);
        dest.writeString(this.logoImage);
        dest.writeString(this.contentRating);
        dest.writeInt(this.installCount);
        dest.writeString(this.storeName);
        dest.writeString(this.storeId);
        dest.writeString(this.storeLogoUrl);
        dest.writeString(this.storeLogo);
        dest.writeString(this.packagaeName);
        dest.writeString(this.tags);
        dest.writeInt(this.requestedViaMesh);
        dest.writeByte((byte) (isSharedOnMesh ? 1 : 0));
        dest.writeByte((byte) (isSynced ? 1 : 0));
        dest.writeByte((byte) (isRmApps ? 1 : 0));
        dest.writeLong(this.apkSize);
    }
}
