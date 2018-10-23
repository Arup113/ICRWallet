package com.nurdcoder.android.icr_wallet.ui.transfer_amount;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.balance.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentTransferAmountBinding;
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

public class TransferAmountFragment extends BaseFragment<TransferAmountMvpView, TransferAmountPresenter> implements TransferAmountMvpView {

    private FragmentTransferAmountBinding mBinding;

    public static TransferAmountFragment newInstance() {
        Bundle bundle = new Bundle();

        TransferAmountFragment transferAmountFragment = new TransferAmountFragment();
        transferAmountFragment.setArguments(bundle);
        return transferAmountFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transfer_amount;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentTransferAmountBinding) getViewDataBinding();

        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getBalance(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected TransferAmountPresenter initPresenter() {
        return new TransferAmountPresenter();
    }

    @Override
    public void onBalanceLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(), PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        mBinding.fragmentTransferAmountParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentTransferAmountBalanceTv.setVisibility(View.VISIBLE);
        mBinding.fragmentTransferAmountBalanceValueTv.setVisibility(View.VISIBLE);
        mBinding.fragmentTransferAmountBalanceValueTv.setText(apiResponse.getBalance() + "");
    }

    @Override
    public void onBalanceLoadedFailed() {
        mBinding.fragmentTransferAmountParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentTransferAmountParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
        mBinding.fragmentTransferAmountParentPwt.layoutProgressWithTextTv.setText(R.string.balance_failed);
    }
}