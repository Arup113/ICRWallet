package com.nurdcoder.android.icr_wallet.ui.ae_address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.databinding.ActivityAeAddressBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.Toaster;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/3/2018 at 12:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: This class represents the email verification page
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 8/6/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class AEAddressActivity extends BaseActivity<AEAddressMVPView, AEAddressPresenter> implements AEAddressMVPView {

    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    private ActivityAeAddressBinding mBinding;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context context
     **/
    public static void runActivityForResult(Context context) {
        Intent intent = new Intent(context, AEAddressActivity.class);
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

        mBinding = (ActivityAeAddressBinding) getViewDataBinding();

        setClickListener(mBinding.buttonSubmit, mBinding.ibBack);
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected AEAddressPresenter initPresenter() {
        return new AEAddressPresenter();
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
                KeyboardUtils.hideSoftInput(AEAddressActivity.this);
                presenter.verifyCaptcha(mBinding.editTextLabel.getText().toString());
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
            presenter.resetPassword(mBinding.editTextLabel.getText().toString());
        } else {
            Toaster.error(this, message);
        }

    }

    @Override
    public void onPasswordResetRequest(boolean success, String message) {
        if (success) {
            Toaster.success(this, message);
            SignInActivity.runActivity(AEAddressActivity.this, null);
            AEAddressActivity.this.finish();

        } else {
            Toaster.error(this, message);
        }
    }
}
