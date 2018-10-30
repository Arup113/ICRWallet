package com.nurdcoder.android.icr_wallet.ui.aed_address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.Endpoints;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.aed_address.ApiResponse;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;
import com.nurdcoder.android.icr_wallet.databinding.ActivityAedAddressBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.util.helper.Glider;
import com.nurdcoder.android.util.helper.KeyboardUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.nurdcoder.android.util.helper.ShowLog;
import com.nurdcoder.android.util.helper.Toaster;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 8/3/2018 at 12:33 AM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: This class represents the email verification page
 * Last edited by : MD. REZWANUR RAHMAN KHAN on 8/6/2018.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class AEDAddressActivity extends BaseActivity<AEAddressMVPView, AEAddressPresenter> implements AEAddressMVPView {

    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    private ActivityAedAddressBinding mBinding;
    private int mType;
    private int mPosition;
    private Address mData;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context context
     **/
    public static void runActivityForResult(Context context) {
        Intent intent = new Intent(context, AEDAddressActivity.class);
        runCurrentActivity(context, intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_aed_address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting background to window to avoid image resize on keyboard popup
        getWindow().setBackgroundDrawableResource(R.drawable.image_background);
    }

    @Override
    protected void startUI() {
        mBinding = (ActivityAedAddressBinding) getViewDataBinding();
        setClickListener(mBinding.buttonSubmit, mBinding.ibBack);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mType = bundle.getInt(EXTRA_TYPE);
            mPosition = bundle.getInt(EXTRA_POSITION);
            mData = bundle.getParcelable(EXTRA_DATA);
        }

        if (mType == Constants.Integer.ONE) {
            mBinding.editTextLabel.setVisibility(View.VISIBLE);
            mBinding.editTextAddress.setVisibility(View.VISIBLE);
            mBinding.buttonSubmit.setVisibility(View.VISIBLE);
            mBinding.progressBar.setVisibility(View.VISIBLE);
            mBinding.textVerify.setText(R.string.edit_address_header);
            mBinding.editTextLabel.setText(mData.getLabel());
            mBinding.editTextAddress.setText(mData.getAddress());
            mBinding.buttonSubmit.setText(R.string.update);
        } else if (mType == Constants.Integer.TWO) {
            mBinding.textVerify.setText(R.string.detail_address_header);
            mBinding.imageViewAddress.setVisibility(View.VISIBLE);
            mBinding.textViewAddress.setVisibility(View.VISIBLE);
            mBinding.textViewAddress.setText(mData.getAddress());
            String url = Endpoints.Constants.BASE_URL + Endpoints.Constants.QR_CODE + mData.getAddress() + ".png";
            ShowLog.e("Data", url);
            Glider.show(this, url, mBinding.imageViewAddress);
        } else {
            mBinding.editTextLabel.setVisibility(View.VISIBLE);
            mBinding.buttonSubmit.setVisibility(View.VISIBLE);
            mBinding.progressBar.setVisibility(View.VISIBLE);
            mBinding.buttonSubmit.setText(R.string.submit);
            mBinding.textVerify.setText(R.string.add_address_header);
        }
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
                KeyboardUtils.hideSoftInput(AEDAddressActivity.this);
                mBinding.buttonSubmit.setVisibility(View.GONE);
                presenter.aeAddress(mType, mData, mBinding.editTextLabel.getText().toString());
                break;

            case R.id.ib_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onAeAddressSuccessful(ApiResponse apiResponse) {
        mBinding.buttonSubmit.setVisibility(View.VISIBLE);
        SharedPreferencesManager.setStringSetting(this, PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA, new Address(apiResponse.getAddress(), apiResponse.getLabel()));
        if (mType == Constants.Integer.ONE) {
            intent.putExtra(EXTRA_POSITION, mPosition);
        }
        setResult(RESULT_OK, intent);
        onBackPressed();
    }

    @Override
    public void onAeAddressFailed() {
        mBinding.buttonSubmit.setVisibility(View.VISIBLE);
        Toaster.error(this, "Operation Failed");
    }
}
