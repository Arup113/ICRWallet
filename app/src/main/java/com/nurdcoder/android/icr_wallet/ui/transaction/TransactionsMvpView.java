package com.nurdcoder.android.icr_wallet.ui.transaction;

import com.nurdcoder.android.icr_wallet.data.local.transactions.ApiResponse;
import com.nurdcoder.android.icr_wallet.ui.base.MvpView;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 8/8/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : MvpView for Similar Apps.
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 8/8/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 8/8/18.
 * ****************************************************************************
 */

public interface TransactionsMvpView extends MvpView {
    void onTransactionsLoadedSuccessful(ApiResponse apiResponse);

    void onTransactionsLoadedFailed();
}
