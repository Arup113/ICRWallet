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

import static com.nurdcoder.android.icr_wallet.data.helper.Constants.Integer.TWO;

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
                startActivityForResult(i, TWO);
                break;

            case R.id.button_sign_up:
                KeyboardUtils.hideSoftInput(getActivity());
                new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.sendMoney(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString()), Constants.Integer.API_DELAY);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TWO) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                ShowLog.e("Data", result);
                if (!TextUtils.isEmpty(result)) {
                    String[] part = result.split("&");
                    for (int i = 0; i < part.length; i++) {
                        if (part[i].contains("address=")) {
                            mBinding.editTextEmail.setText(part[0].replaceAll("address=", ""));
                        }
                        if (part[i].contains("amount=")) {
                            mBinding.editTextPassword.setText(part[1].replaceAll("amount=", ""));
                        }
                    }
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