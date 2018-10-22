package com.nurdcoder.android.icr_wallet.ui.transaction;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.Constants;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.data.local.transactions.ApiResponse;
import com.nurdcoder.android.icr_wallet.data.local.transactions.Transaction;
import com.nurdcoder.android.icr_wallet.databinding.FragmentTransactionsBinding;
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

public class TransactionsFragment extends BaseFragment<TransactionsMvpView, TransactionsPresenter> implements TransactionsMvpView {

    private FragmentTransactionsBinding mBinding;

    private ArrayList<Transaction> mDataList;
    private TransactionsRecyclerAdapter mAdapter;


    public static TransactionsFragment newInstance() {
        Bundle bundle = new Bundle();

        TransactionsFragment transactionsFragment = new TransactionsFragment();
        transactionsFragment.setArguments(bundle);
        return transactionsFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transactions;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentTransactionsBinding) getViewDataBinding();

        mDataList = new ArrayList<>();
        mBinding.fragmentTransactionsParentRv.setHasFixedSize(true);
        mAdapter = new TransactionsRecyclerAdapter(mDataList, getContext());

        mBinding.fragmentTransactionsParentRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.fragmentTransactionsParentRv.addItemDecoration(new CustomRecyclerItemSpaceDecoration(ScreenUtils.dp2px(Objects.requireNonNull(getContext()), 10), 0, ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10), ScreenUtils.dp2px(getContext(), 10)));
        mBinding.fragmentTransactionsParentRv.setAdapter(mAdapter);

        // Fling listener for nested scrolling
        mBinding.fragmentTransactionsParentRv.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                mBinding.fragmentTransactionsParentRv.dispatchNestedFling(velocityX, velocityY, false);
                return false;
            }
        });

        new Handler(Objects.requireNonNull(getActivity()).getMainLooper()).postDelayed(() -> presenter.getTransactions(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected TransactionsPresenter initPresenter() {
        return new TransactionsPresenter();
    }

    @Override
    public void onTransactionsLoadedSuccessful(ApiResponse apiResponse) {
        SharedPreferencesManager.setStringSetting(getContext(),PreferenceKey.KEY_USER_TOKEN,apiResponse.getToken());
        mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        if (apiResponse.getTransactions().size() > 0) {
            mBinding.fragmentTransactionsParentRv.setVisibility(View.VISIBLE);
            mDataList.addAll(apiResponse.getTransactions());
            mAdapter.notifyDataSetChanged();
        } else {
            mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
            mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextTv.setText(R.string.transactions_empty);
        }
    }

    @Override
    public void onTransactionsLoadedFailed() {
        mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextPb.setVisibility(View.GONE);
        mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextTv.setVisibility(View.VISIBLE);
        mBinding.fragmentTransactionsParentPwt.layoutProgressWithTextTv.setText(R.string.transactions_failed);
    }
}