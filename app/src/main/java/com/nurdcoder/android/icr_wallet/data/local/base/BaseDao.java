package com.nurdcoder.android.icr_wallet.data.local.base;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

public interface BaseDao<T> {
    /**
     * Insert a entity in the database. If the entity already exists, replace it.
     *
     * @param entity the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);

    /**
     * Update a entity in the database.
     *
     * @param entity the entity to be updated.
     */
    @Update
    int update(T... entity);

    /**
     * Delete a entity in the database.
     *
     * @param entity the entity to be delete.
     */
    @Delete
    void delete(T... entity);
}