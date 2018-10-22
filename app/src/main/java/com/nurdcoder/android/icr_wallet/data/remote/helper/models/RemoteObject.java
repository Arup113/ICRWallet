package com.nurdcoder.android.icr_wallet.data.remote.helper.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [19-Jul-2018 at 1:13 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [19-Jul-2018 at 1:13 PM].
 * * --> <Second Editor> on [19-Jul-2018 at 1:13 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [19-Jul-2018 at 1:13 PM].
 * * --> <Second Reviewer> on [19-Jul-2018 at 1:13 PM].
 * * ============================================================================
 **/

public class RemoteObject {

    final Object mutex = new Object();
    Map<String, Object> serverData = new HashMap<>();

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

    public RemoteObject() {

    }

    public static RemoteObject create() {
        return new RemoteObject();
    }
}
