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

package com.nurdcoder.android.icr_wallet.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.databinding.ActivityMainBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.home.HomeFragment;
import com.nurdcoder.android.icr_wallet.ui.my_addresses.MyAddressesFragment;
import com.nurdcoder.android.icr_wallet.ui.my_key.MyKeyFragment;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.icr_wallet.ui.transactions.TransactionsFragment;
import com.nurdcoder.android.icr_wallet.ui.transfer_amount.TransferAmountFragment;
import com.nurdcoder.android.util.helper.BarUtil;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.ScreenUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;

import java.util.Objects;

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

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding mBinding;

    private AppCompatImageView mUserAvatar;
    private TextView mUserName;
    private TextView mUserEmail;
    private Toolbar mToolbar;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setUpToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBinding.drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityMainBinding) getViewDataBinding();
        setUpToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // Do whatever you want here
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                KeyboardUtils.hideSoftInput(MainActivity.this);
            }
        };
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);

        setUpNavHeader();
        commitFragment(R.id.main_fragment_container, HomeFragment.newInstance());
        setTitle(getString(R.string.home));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNavHeaderData();
    }

    private void setUpNavHeader() {
        View hView = mBinding.navView.getHeaderView(0);
        mUserAvatar = hView.findViewById(R.id.nav_header_main_avatar_iv);
        mUserName = hView.findViewById(R.id.nav_header_main_name_tv);
        mUserEmail = hView.findViewById(R.id.nav_header_main_email_tv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarHeight = BarUtil.getStatusBarHeight(Objects.requireNonNull(this));
            ScreenUtils.setMarginInPixel(mUserAvatar, 0, statusBarHeight, 0, 0);
        }
    }

    private void setNavHeaderData() {
//        ParseFile imageFile = ParseUser.getCurrentUser().getParseFile(Constants.KEY_PICTURE);
//        if (imageFile != null) {
//            Glider.showCircleImage(imageFile.getUrl(), mUserAvatar);
//        }
        mUserName.setText(SharedPreferencesManager.getStringSetting(this, PreferenceKey.KEY_USER_NAME, ""));
        mUserEmail.setText(SharedPreferencesManager.getStringSetting(this, PreferenceKey.KEY_USER_EMAIL, ""));
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                commitFragment(R.id.main_fragment_container, HomeFragment.newInstance());
                setTitle(getString(R.string.home));
                break;
            case R.id.nav_my_addresses:
                commitFragment(R.id.main_fragment_container, MyAddressesFragment.newInstance());
                setTitle(getString(R.string.my_addresses));
                break;
            case R.id.nav_transfer_amount:
                commitFragment(R.id.main_fragment_container, TransferAmountFragment.newInstance());
                setTitle(getString(R.string.transfer_amount));
                break;
            case R.id.nav_transactions:
                commitFragment(R.id.main_fragment_container, TransactionsFragment.newInstance());
                setTitle(getString(R.string.transactions));
                break;
            case R.id.nav_my_key:
                commitFragment(R.id.main_fragment_container, MyKeyFragment.newInstance());
                setTitle(getString(R.string.my_key));
                break;
            case R.id.nav_log_out:
                SharedPreferencesManager.setBooleanSetting(this, PreferenceKey.KEY_IS_LOGGED_IN, false);
                SignInActivity.runActivity(this, null);
                finish();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
