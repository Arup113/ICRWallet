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

package com.nurdcoder.android.icr_wallet.ui.sign_in;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.databinding.ActivityLoginBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.email_verification.EmailVerificationActivity;
import com.nurdcoder.android.icr_wallet.ui.main.MainActivity;
import com.nurdcoder.android.icr_wallet.ui.sign_up.SignUpActivity;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.ProgressDialogHelper;
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

public class SignInActivity extends BaseActivity<SignInMvpView, SignInPresenter> implements SignInMvpView {

    private ActivityLoginBinding mBinding;
    private ProgressDialogHelper progressDialogHelper;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context context
     **/
    public static void runActivity(Context context, String text) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (text != null) intent.putExtra("message", text);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting background to window to avoid image resize on keyboard popup
        getWindow().setBackgroundDrawableResource(R.drawable.image_background);

        // deep linking
//        if (getIntent() != null) {
//            if (getIntent().getData() != null && getIntent().getData().toString().contains("token")) {
//
//                presenter.verifyUser(getIntent().getData().toString());
//
//                //enable progress while verification on going
//            }
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void startUI() {

        mBinding = (ActivityLoginBinding) getViewDataBinding();

        progressDialogHelper = new ProgressDialogHelper(this, "Logging In...");

        // Setting right drawable
        setRightDrawable(mBinding.editTextEmailAddress, R.drawable.ic_mail);
        setRightDrawable(mBinding.editTextPassword, R.drawable.ic_eye_closed);

        mBinding.editTextPassword.setOnTouchListener((v, event) -> {

            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (mBinding.editTextPassword.getRight() - mBinding.editTextPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

//                        mBinding.editTextPassword.setSelection(mBinding.editTextPassword.getText().length());

                    mBinding.editTextPassword.requestFocus();

                    if (mBinding.editTextPassword.getTransformationMethod() == null) {
                        mBinding.editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
                        setRightDrawable(mBinding.editTextPassword, R.drawable.ic_eye_closed);
                    } else {
                        mBinding.editTextPassword.setTransformationMethod(null);
                        setRightDrawable(mBinding.editTextPassword, R.drawable.ic_eye);
                    }
                    return true;
                }
            }
            return false;
        });


        setClickListener(
                mBinding.buttonSignIn,
                mBinding.buttonSignUp,
                mBinding.buttonForgotPassword
        );


        if (getIntent().getExtras() != null && getIntent().getExtras().getString("message") != null) {
            Toaster.show(this, getIntent().getExtras().getString("message"));
        }

    }

    public void setRightDrawable(EditText view, int drawableId) {
        view.setCompoundDrawablesWithIntrinsicBounds(null, null,
                AppCompatResources.getDrawable(this, drawableId), null);
    }

    @Override
    protected void stopUI() {
    }

    @Override
    protected SignInPresenter initPresenter() {
        return new SignInPresenter(SignInActivity.this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_sign_in:
                KeyboardUtils.hideSoftInput(SignInActivity.this);
                presenter.validateUserInput(mBinding.editTextEmailAddress.getText().toString(), mBinding.editTextPassword.getText().toString());
                break;

            case R.id.button_sign_up:
                SignUpActivity.runActivity(this);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;

            case R.id.button_forgot_password:
                EmailVerificationActivity.runActivity(this);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
        }
    }

    @Override
    public void onSignIn(boolean isSuccess, String message) {
        progressDialogHelper.hide();
        if (isSuccess) {
            MainActivity.runActivity(SignInActivity.this);
            overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            finish();
        } else {
            if (!message.isEmpty()) {
                Toaster.error(this, message);
            }
        }
    }

    @Override
    public void isValidUserInput(boolean isValid, String message) {
        if (isValid) {
            progressDialogHelper.show();
            presenter.signIn(mBinding.editTextEmailAddress.getText().toString(), mBinding.editTextPassword.getText().toString());
        } else {
            Toaster.error(this, message);
        }
    }

    @Override
    public void onUserVerified(boolean isVerified, String message) {
        if (isVerified) {
            runOnUiThread(() -> Toaster.success(this, getString(R.string.email_verified_now_login)));

        } else {
            runOnUiThread(() -> Toaster.error(this, getString(R.string.email_not_verified)));

        }
    }
}