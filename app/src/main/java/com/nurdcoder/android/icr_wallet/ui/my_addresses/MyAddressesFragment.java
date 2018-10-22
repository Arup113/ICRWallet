package com.nurdcoder.android.icr_wallet.ui.my_addresses;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.ApiResponse;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;
import com.nurdcoder.android.icr_wallet.databinding.FragmentMyAddressesBinding;
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

public class MyAddressesFragment extends BaseFragment<MyAddressesMvpView, MyAddressesPresenter> implements MyAddressesMvpView {

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
        mAdapter = new MyAddressesRecyclerAdapter(mDataList, getContext());

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

        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getMyAddresses(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected MyAddressesPresenter initPresenter() {
        return new MyAddressesPresenter();
    }

    @Override
    public void onMyAddressesLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(),PreferenceKey.KEY_USER_TOKEN,apiResponse.getToken());
        mBinding.fragmentMyAddressesParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
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
}