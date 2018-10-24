package com.nurdcoder.android.icr_wallet.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.balance.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentHomeBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseFragment;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;

import java.util.Objects;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 8/8/18
 * * Email : muktadir@w3engineers.com
 * *
 * * Purpose : Fragment for Similar Apps.
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 8/8/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 8/8/18.
 * ****************************************************************************
 */

public class HomeFragment extends BaseFragment<HomeMvpView, HomePresenter> implements HomeMvpView {

    private FragmentHomeBinding mBinding;

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentHomeBinding) getViewDataBinding();

        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getBalance(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onBalanceLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(), PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        mBinding.fragmentHomeParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentHomeBalanceTv.setVisibility(View.VISIBLE);
        mBinding.fragmentHomeBalanceValueTv.setVisibility(View.VISIBLE);
        mBinding.fragmentHomeKeyTv.setVisibility(View.VISIBLE);
        mBinding.fragmentHomeKeyIv.setVisibility(View.VISIBLE);
        mBinding.fragmentHomeBalanceValueTv.setText(apiResponse.getBalance() + "");
    }

    @Override
    public void onBalanceLoadedFailed() {
        mBinding.fragmentHomeParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentHomeParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
        mBinding.fragmentHomeParentPwt.layoutProgressWithTextTv.setText(R.string.balance_failed);
    }
}