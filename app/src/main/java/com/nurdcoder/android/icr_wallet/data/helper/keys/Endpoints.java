package com.nurdcoder.android.icr_wallet.data.helper.keys;


/**
 * Created by: Mithun Sarker on 8/29/18 at 4:27 PM.
 * Email: mithun@w3engineers.com
 * Code Responsibility: This class holds list of endpoints for different APIs
 * Last edited by : Rezwanur on 9/3/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public interface Endpoints {
    interface Flags {
        String RECAPTCHA = "recaptcha";
        String LOG_IN = Constants.BASE_URL + Constants.API + "login";
        String SIGN_UP = Constants.BASE_URL + Constants.API + "register";
        String TRANSACTIONS = Constants.BASE_URL + Constants.API + "gettransactions";
        String MY_ADDRESSES = Constants.BASE_URL + Constants.API + "getaddress";
        String BALANCE = Constants.BASE_URL + Constants.API + "getbalance";
        String MY_KEY = Constants.BASE_URL + Constants.API + "getbalance";
        String AE_ADDRESS = Constants.BASE_URL + Constants.API + "putaddresslebel";
        String SEND_MONEY = Constants.BASE_URL + Constants.API + "sendamount";
    }

    interface Keys {
        String TOKEN = "token";
        String ADDRESS = "address";
        String LABEL = "label";
        String AMOUNT = "amount";
    }

    interface Values {
    }

    interface Constants {
        String BASE_URL = "https://icr-wallet.com/";
        String API = "api/";
        String QR_CODE = "qrcode/";
    }
}
