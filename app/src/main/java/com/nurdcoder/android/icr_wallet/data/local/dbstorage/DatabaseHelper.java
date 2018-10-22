package com.nurdcoder.android.icr_wallet.data.local.dbstorage;

import com.nurdcoder.android.icr_wallet.data.local.user.UserService;

/*
* ****************************************************************************
* * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
* *
* * Created by:
* * Name : Ahmed Mohmmad Ullah (Azim)
* * Date : 2/15/18
* * Email : azim@w3engineers.com
* *
* * Purpose: Enables injection of data sources.
* *
* * Last Edited by : Rezwanur on 10/05/18.
* * History: Comment Added
* * 1:
* * 2:
* *
* * Last Reviewed by : Rezwanur on 10/05/18.
* ****************************************************************************
*/


public class DatabaseHelper {

    public static UserService provideArticleService() {
        return new UserService(DatabaseService.on().userDao());
    }
    public static UserService provideUserService() {
        return new UserService(DatabaseService.on().userDao());
    }
}