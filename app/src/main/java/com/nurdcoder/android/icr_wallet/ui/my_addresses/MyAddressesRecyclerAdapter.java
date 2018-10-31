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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;

import java.util.ArrayList;

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

public class MyAddressesRecyclerAdapter extends RecyclerView.Adapter<MyAddressesRecyclerAdapter.SingleItemRowHolder> {

    private ArrayList<Address> mDataList;
    private Context mContext;
    private MyAddressOperationListener mMyAddressOperationListener;

    public MyAddressesRecyclerAdapter(ArrayList<Address> mDataList, Context mContext, MyAddressOperationListener mMyAddressOperationListener) {
        this.mDataList = mDataList;
        this.mContext = mContext;
        this.mMyAddressOperationListener = mMyAddressOperationListener;
    }

    @NonNull
    @Override
    public SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_addresses, parent, false);
        return new SingleItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleItemRowHolder holder, int position) {
        final Address itemModel = mDataList.get(position);
        holder.list_item_my_addresses_label_tv.setText(itemModel.getLabel());

        holder.list_item_my_addresses_address_tv.setText(itemModel.getAddress());
        holder.list_item_my_addresses_edit_acid.setTag(position);
        holder.list_item_my_addresses_parent_vll.setTag(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return (null != mDataList ? mDataList.size() : 0);
    }

    class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private TextView list_item_my_addresses_label_tv;
        private TextView list_item_my_addresses_address_tv;
        private AppCompatImageButton list_item_my_addresses_edit_acid;
        private LinearLayout list_item_my_addresses_parent_vll;

        SingleItemRowHolder(View itemView) {
            super(itemView);
            this.list_item_my_addresses_label_tv = itemView.findViewById(R.id.list_item_my_addresses_label_tv);
            this.list_item_my_addresses_address_tv = itemView.findViewById(R.id.list_item_my_addresses_address_tv);
            this.list_item_my_addresses_edit_acid = itemView.findViewById(R.id.list_item_my_addresses_edit_acid);
            this.list_item_my_addresses_parent_vll = itemView.findViewById(R.id.list_item_my_addresses_parent_vll);

            list_item_my_addresses_edit_acid.setOnClickListener(v -> {
                int position = (int) v.getTag();
                mMyAddressOperationListener.onAddressEditClicked(position);
            });

            list_item_my_addresses_parent_vll.setOnClickListener(v -> {
                int position = (int) v.getTag();
                mMyAddressOperationListener.onAddressClicked(position);
            });
        }
    }
}
