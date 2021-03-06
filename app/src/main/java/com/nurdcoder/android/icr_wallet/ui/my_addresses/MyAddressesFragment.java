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

package com.nurdcoder.android.icr_wallet.ui.my_addresses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.ApiResponse;
import com.nurdcoder.android.icr_wallet.databinding.FragmentMyAddressesBinding;
import com.nurdcoder.android.icr_wallet.ui.aed_address.AEDAddressActivity;
import com.nurdcoder.android.icr_wallet.ui.base.BaseFragment;
import com.nurdcoder.android.util.helper.CustomRecyclerItemSpaceDecoration;
import com.nurdcoder.android.util.helper.ScreenUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Objects;

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

public class MyAddressesFragment extends BaseFragment<MyAddressesMvpView, MyAddressesPresenter> implements MyAddressesMvpView, MyAddressOperationListener {

    private FragmentMyAddressesBinding mBinding;

    private ArrayList<Address> mDataList;
    private MyAddressesRecyclerAdapter mAdapter;


    public static MyAddressesFragment newInstance() {
        Bundle bundle = new Bundle();

        MyAddressesFragment myAddressesFragment = new MyAddressesFragment();
        myAddressesFragment.setArguments(bundle);
        return myAddressesFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_addresses;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentMyAddressesBinding) getViewDataBinding();

        mDataList = new ArrayList<>();
        mBinding.fragmentMyAddressesParentRv.setHasFixedSize(true);
        mAdapter = new MyAddressesRecyclerAdapter(mDataList, getContext(), this);

        mBinding.fragmentMyAddressesParentRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.fragmentMyAddressesParentRv.addItemDecoration(new CustomRecyclerItemSpaceDecoration(ScreenUtils.dp2px(Objects.requireNonNull(getContext()), 10), 0, ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10)));
        mBinding.fragmentMyAddressesParentRv.setAdapter(mAdapter);

        // Fling listener for nested scrolling
        mBinding.fragmentMyAddressesParentRv.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                mBinding.fragmentMyAddressesParentRv.dispatchNestedFling(velocityX, velocityY, false);
                return false;
            }
        });

        setClickListener(mBinding.fragmentMyAddressesParentFab);
        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getMyAddresses(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.fragment_my_addresses_parent_fab:
                Intent intent = new Intent(getContext(), AEDAddressActivity.class);
                intent.putExtra(AEDAddressActivity.EXTRA_TYPE, Constants.Integer.ZERO);
                startActivityForResult(intent, Constants.Integer.ZERO);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Address address = Objects.requireNonNull(data.getExtras()).getParcelable(AEDAddressActivity.EXTRA_DATA);
            switch (requestCode) {
                case Constants.Integer.ZERO:
                    mDataList.add(address);
                    mAdapter.notifyDataSetChanged();
                    if (mDataList.size() == Constants.Integer.ONE) {
                        mBinding.fragmentMyAddressesParentRv.setVisibility(View.VISIBLE);
                        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextTv.setVisibility(View.GONE);
                    }
                    break;
                case Constants.Integer.ONE:
                    int position = data.getExtras().getInt(AEDAddressActivity.EXTRA_POSITION);
                    mDataList.remove(position);
                    mDataList.add(position, address);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    protected MyAddressesPresenter initPresenter() {
        return new MyAddressesPresenter();
    }

    @Override
    public void onMyAddressesLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(), PreferenceKey.KEY_USER_TOKEN, apiResponse.getToken());
        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentMyAddressesParentFab.setVisibility(View.VISIBLE);
        if (apiResponse.getAddress().size() > 0) {
            mBinding.fragmentMyAddressesParentRv.setVisibility(View.VISIBLE);
            mDataList.addAll(apiResponse.getAddress());
            mAdapter.notifyDataSetChanged();
        } else {
            mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
            mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextTv.setText(R.string.transactions_empty);
        }
    }

    @Override
    public void onMyAddressesLoadedFailed() {
        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextTv.setText(R.string.transactions_failed);
    }

    @Override
    public void onAddressClicked(int position) {
        Intent intent = new Intent(getContext(), AEDAddressActivity.class);
        intent.putExtra(AEDAddressActivity.EXTRA_TYPE, Constants.Integer.TWO);
        intent.putExtra(AEDAddressActivity.EXTRA_POSITION, position);
        intent.putExtra(AEDAddressActivity.EXTRA_DATA, mDataList.get(position));
        startActivity(intent);
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @Override
    public void onAddressEditClicked(int position) {
        Intent intent = new Intent(getContext(), AEDAddressActivity.class);
        intent.putExtra(AEDAddressActivity.EXTRA_TYPE, Constants.Integer.ONE);
        intent.putExtra(AEDAddressActivity.EXTRA_POSITION, position);
        intent.putExtra(AEDAddressActivity.EXTRA_DATA, mDataList.get(position));
        startActivityForResult(intent, Constants.Integer.ONE);
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}