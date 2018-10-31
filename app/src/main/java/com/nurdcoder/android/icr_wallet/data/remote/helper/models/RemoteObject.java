/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.nurdcoder.android.icr_wallet.data.remote.helper.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class RemoteObject {

    final Object mutex = new Object();
    Map<String, Object> serverData = new HashMap<>();

    public RemoteObject() {

    }

    public static RemoteObject create() {
        return new RemoteObject();
    }

    public void put(String key, Object value) {
        serverData.put(key, value);
    }

    public boolean containsKey(String key) {
        synchronized (mutex) {
            return serverData.containsKey(key);
        }
    }

    /**
     * Returns a set view of the keys contained in this object. This does not include createdAt,
     * updatedAt, authData, or objectId. It does include things like username and ACL.
     */
    public Set<String> keySet() {
        synchronized (mutex) {
            return Collections.unmodifiableSet(serverData.keySet());
        }
    }

    /**
     * Access a {@link String} value.
     *
     * @param key The key to access the value for.
     * @return {@code null} if there is no such key or if it is not a {@link String}.
     */
    public String getString(String key) {
        synchronized (mutex) {
            Object value = serverData.get(key);

            if (!(value instanceof String)) {
                return null;
            }

            return (String) value;
        }
    }

    /**
     * Access a {@link Object} value.
     *
     * @param key The key to access the value for.
     * @return {@code null} if there is no such key or if it is not a {@link Object}.
     */
    public Object get(String key) {
        synchronized (mutex) {
            Object value = serverData.get(key);

            if (value == null) {
                return null;
            }

            return value;
        }
    }
}
