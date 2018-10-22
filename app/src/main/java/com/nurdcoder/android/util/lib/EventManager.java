package com.nurdcoder.android.util.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : Roman Hawlader
 * * Date : 08/28/17
 * * Email : sudipta@w3engineers.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 2/23/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 03/13/18.
 * ****************************************************************************
 */

public class EventManager {
    public static void register(Object subscriber) {
        if (!isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        if (isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    public static boolean isRegistered(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }
}