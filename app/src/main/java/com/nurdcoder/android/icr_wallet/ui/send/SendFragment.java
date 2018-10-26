package com.nurdcoder.android.icr_wallet.ui.send;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.balance.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentHomeBinding;
import com.nurdcoder.android.icr_wallet.databinding.FragmentSendMoneyBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseFragment;
import com.nurdcoder.android.util.helper.KeyboardUtils;
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

public class SendFragment extends BaseFragment<SendMvpView, SendPresenter> implements SendMvpView {

    private FragmentSendMoneyBinding mBinding;

    public static SendFragment newInstance() {
        Bundle bundle = new Bundle();

        SendFragment homeFragment = new SendFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_send_money;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentSendMoneyBinding) getViewDataBinding();
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
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                Log.e("Data", result);
                if (!TextUtils.isEmpty(result))
                    mBinding.editTextEmail.setText(result);
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
    protected SendPresenter initPresenter() {
        return new SendPresenter();
    }

    @Override
    public void onBalanceLoadedSuccessful(ApiResponse apiResponse) {

    }

    @Override
    public void onBalanceLoadedFailed() {

    }
}