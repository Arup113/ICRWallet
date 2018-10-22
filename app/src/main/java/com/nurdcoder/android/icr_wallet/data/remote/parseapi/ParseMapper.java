package com.nurdcoder.android.icr_wallet.data.remote.parseapi;

import android.support.annotation.NonNull;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import com.nurdcoder.android.icr_wallet.data.remote.helper.models.RemoteObject;
import com.nurdcoder.android.icr_wallet.data.remote.helper.models.UserModel;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [13-Jul-2018 at 1:04 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [13-Jul-2018 at 1:04 PM].
 * * --> <Second Editor> on [13-Jul-2018 at 1:04 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [13-Jul-2018 at 1:04 PM].
 * * --> <Second Reviewer> on [13-Jul-2018 at 1:04 PM].
 * * ============================================================================
 **/

class ParseMapper {
    @NonNull
    ParseUser getParseUser(String username, String password, RemoteObject remoteObject) {
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);

        for (String key : remoteObject.keySet()) {
            user.put(key, remoteObject.get(key));
        }

        return user;
    }

    public UserModel getUser(ParseUser parseUser, UserModel userModel) {

        if (parseUser != null) {
            userModel = new UserModel();

            userModel.setFullName(parseUser.getString("fullName"));
            userModel.setUserName(parseUser.getUsername());
            userModel.setEmail(parseUser.getEmail());
            userModel.setPhoneNumber(parseUser.getString("phoneNumber"));
            userModel.setEmailVerified(parseUser.getBoolean("emailVerified"));
        }

        return userModel;
    }
}