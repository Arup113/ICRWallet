package com.nurdcoder.android.icr_wallet.ui.sign_up;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.databinding.ActivitySignUpBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.util.helper.Glider;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.ProgressDialogHelper;
import com.nurdcoder.android.util.helper.Toaster;
import com.parse.ParseUser;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/1/2018 at 1:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: This class represents the sign up page
 * Last edited by : Rezwanur on 10/10/2018.
 * Last Reviewed by : Rezwanur on 10/10/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class SignUpActivity extends BaseActivity<SignUpMvpView, SignUpPresenter> implements SignUpMvpView {

    private ActivitySignUpBinding mBinding;
    private Uri mProfileImageUri;
    private ProgressDialogHelper mProgressDialogHelper;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context
     **/
    public static void runActivity(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting background to window to avoid image resize on keyboard popup
        getWindow().setBackgroundDrawableResource(R.drawable.image_background);
    }


    @Override
    protected void startUI() {

        mBinding = (ActivitySignUpBinding) getViewDataBinding();
        setClickListener(
                mBinding.ivProfile,
                mBinding.buttonSignUp,
                mBinding.ibBack
        );
    }

    @Override
    protected void stopUI() {

    }


    @Override
    protected SignUpPresenter initPresenter() {
        return new SignUpPresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up:

                KeyboardUtils.hideSoftInput(SignUpActivity.this);
                mProgressDialogHelper = new ProgressDialogHelper(this, "Signing Up...");
                mProgressDialogHelper.show();

                //validate user input
                presenter.userInputValidation(mBinding.editTextName.getText().toString().trim(),
                        mBinding.editTextEmail.getText().toString().trim(),
                        mBinding.editTextPassword.getText().toString().trim(),
                        mBinding.editTextConfirmPassword.getText().toString().trim());

                break;

            case R.id.iv_profile:

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setActivityTitle(getString(R.string.app_name))
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setCropMenuCropButtonTitle("Crop")
                        .setFixAspectRatio(true)
                        .setAspectRatio(5, 5)
                        .setOutputCompressQuality(30)
                        .start(this);

                break;

            case R.id.ib_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    mProfileImageUri = result.getUri();
                    if (mProfileImageUri != null) {
                        Glider.show(this, mProfileImageUri.getPath(), mBinding.ivProfile);
                    } else {
                        Toaster.show(this, "No image is set to show");
                    }
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onSignUp(boolean isSuccess, String message) {
        mProgressDialogHelper.hide();
        if (isSuccess) {
            onBackPressed();
        } else {
            if (!message.isEmpty()) {
                Toaster.error(this, message);
            }
        }
    }

    @Override
    public void isValidInput(boolean isValid, String message) {
        if (isValid) {
            //initially we are not verifying email, after the completion of design we will verifyCaptcha
            presenter.signUp(
                    mBinding.editTextName.getText().toString(),
                    mBinding.editTextEmail.getText().toString(),
                    mBinding.editTextPassword.getText().toString(),
                    mProfileImageUri
            );
        } else {
            mProgressDialogHelper.hide();
            Toaster.error(this, message);
        }
    }
}
