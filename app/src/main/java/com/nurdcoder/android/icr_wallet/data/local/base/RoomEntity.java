package com.nurdcoder.android.icr_wallet.data.local.base;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.nurdcoder.android.icr_wallet.data.local.dbstorage.ColumnNames;

public class RoomEntity extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ColumnNames.ID)
    @NonNull
    public long mId;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

}