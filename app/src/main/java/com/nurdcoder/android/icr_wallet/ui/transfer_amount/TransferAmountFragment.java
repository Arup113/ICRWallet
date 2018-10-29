package com.nurdcoder.android.icr_wallet.ui.transfer_amount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.transfer_amount.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentTransferAmountBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseFragment;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.helper.ShowLog;
import com.nurdcoder.android.util.helper.Toaster;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                Intent i = new Intent(getActivity(), ScanActivity.class);
                startActivityForResult(i, 11);
                break;

            case R.id.button_sign_up:
                KeyboardUtils.hideSoftInput(getActivity());
                new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.sendMoney(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString()), Constants.Integer.API_DELAY);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                ShowLog.e("Data", result);
                if (!TextUtils.isEmpty(result)) {
                    String[] part  = result.split("&");
                    mBinding.editTextEmail.setText(part[0].replaceAll("address=",""));
                    mBinding.editTextPassword.setText(part[1].replaceAll("amount=",""));
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    protected void startUI() {
        setClickListener(
                mBinding.buttonSignIn,
                mBinding.buttonSignUp
        );
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected TransferAmountPresenter initPresenter() {
        return new TransferAmountPresenter();
    }

    @Override
    public void onTransferAmountSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(), PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        Toaster.success(getContext(), apiResponse.getMessage());
        mBinding.editTextEmail.setText("");
        mBinding.editTextPassword.setText("");
    }

    @Override
    public void onTransferAmountFailed() {
        Toaster.error(getContext(), "Transfer Amount Failed");
    }
}