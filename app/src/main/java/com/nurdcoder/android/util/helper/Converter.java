package com.nurdcoder.android.util.helper;

import android.arch.persistence.room.TypeConverter;

import com.nurdcoder.android.util.lib.GSonHelper;

import java.io.Serializable;

/**
 * Created by: Mithun Sarker on 9/3/18 at 5:10 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: Converter for saving complex object to ROOM
 * Last edited by : <NAME> on <DATE>.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class Converter implements Serializable {

    @TypeConverter
    public static String packageToString(Package pc) {
        return pc == null ? null : GSonHelper.toJson(pc);
    }


    @TypeConverter
    public static Package stringToPackage(String pc) {
        return pc == null ? null : GSonHelper.fromJson(pc, Package.class);
    }
}
