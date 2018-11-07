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

package com.nurdcoder.android.icr_wallet.ui.email_verification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.databinding.ActivityEmailVerificationBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.Toaster;

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

public class EmailVerificationActivity extends BaseActivity<EmailVerificationMVPView, EmailVerificationPresenter> implements EmailVerificationMVPView {


    private ActivityEmailVerificationBinding mBinding;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context context
     **/
    public static void runActivity(Context context) {
        Intent intent = new Intent(context, EmailVerificationActivity.class);
        runCurrentActivity(context, intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_verification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting background to window to avoid image resize on keyboard popup
        getWindow().setBackgroundDrawableResource(R.drawable.image_background);
    }

    @Override
    protected void startUI() {
        mBinding = (ActivityEmailVerificationBinding) getViewDataBinding();
        setClickListener(mBinding.buttonSubmit, mBinding.ibBack);
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected EmailVerificationPresenter initPresenter() {
        return new EmailVerificationPresenter();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit:
                KeyboardUtils.hideSoftInput(EmailVerificationActivity.this);
                presenter.verifyCaptcha(mBinding.editTextEmailAddress.getText().toString());
                break;

            case R.id.ib_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCaptchaVerified(boolean isSuccess, String message) {
        if (isSuccess) {
//            Toaster.success(message);
            presenter.resetPassword(mBinding.editTextEmailAddress.getText().toString());
        } else {
            Toaster.error(this, message);
        }
    }

    @Override
    public void onPasswordResetRequest(boolean success, String message) {
        if (success) {
            Toaster.success(this, message);
            SignInActivity.runActivity(EmailVerificationActivity.this, null);
            EmailVerificationActivity.this.finish();

        } else {
            Toaster.error(this, message);
        }
    }
}
