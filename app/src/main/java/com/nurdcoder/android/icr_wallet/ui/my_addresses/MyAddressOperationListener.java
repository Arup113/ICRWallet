package com.nurdcoder.android.icr_wallet.ui.my_addresses;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 9/20/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : Presenter for App Reviews & Ratings.
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 9/20/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 9/20/18.
 * ****************************************************************************
 */

public interface MyAddressOperationListener {
    void onAddressClicked(int position);

    void onAddressEditClicked(int position);
}