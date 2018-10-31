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

package com.nurdcoder.android.icr_wallet.ui.my_key;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.my_key.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentMyKeyBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseFragment;
import com.nurdcoder.android.util.helper.Glider;
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

public class MyKeyFragment extends BaseFragment<MyKeyMvpView, MyKeyPresenter> implements MyKeyMvpView {

    private FragmentMyKeyBinding mBinding;

    public static MyKeyFragment newInstance() {
        Bundle bundle = new Bundle();

        MyKeyFragment myKeyFragment = new MyKeyFragment();
        myKeyFragment.setArguments(bundle);
        return myKeyFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_key;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentMyKeyBinding) getViewDataBinding();

        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getMyKey(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected MyKeyPresenter initPresenter() {
        return new MyKeyPresenter();
    }

    @Override
    public void onMyKeyDataLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(), PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        mBinding.fragmentMyKeyParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);

        mBinding.fragmentMyKeyKeyTv.setVisibility(View.VISIBLE);
        mBinding.fragmentMyKeyKeyValueTv.setVisibility(View.VISIBLE);
        mBinding.fragmentMyKeyKeyIv.setVisibility(View.VISIBLE);

        mBinding.fragmentMyKeyKeyValueTv.setText(apiResponse.getAddress());
        Glider.show(getContext(), apiResponse.getQrcode(), mBinding.fragmentMyKeyKeyIv);
    }

    @Override
    public void onMyKeyDataLoadedFailed() {
        mBinding.fragmentMyKeyParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentMyKeyParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
        mBinding.fragmentMyKeyParentPwt.layoutProgressWithTextTv.setText(R.string.my_key_failed);
    }
}