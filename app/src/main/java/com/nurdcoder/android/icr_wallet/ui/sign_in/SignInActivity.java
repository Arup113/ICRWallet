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
 * Created by: MD. REZWANUR RAHMAN KHAN on 7/31/2018 at 11:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: This class represents the sign in page
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 8/1/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
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
        setRightDrawable(mBinding.editTextEmail, R.drawable.ic_mail);
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
                presenter.validateUserInput(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString());
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
            presenter.signIn(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString());
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