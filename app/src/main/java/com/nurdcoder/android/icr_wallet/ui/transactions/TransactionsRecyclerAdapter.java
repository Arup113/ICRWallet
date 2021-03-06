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

package com.nurdcoder.android.icr_wallet.ui.transactions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.local.transactions.Transaction;

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

public class TransactionsRecyclerAdapter extends RecyclerView.Adapter<TransactionsRecyclerAdapter.SingleItemRowHolder> {

    private ArrayList<Transaction> mDataList;
    private Context mContext;

    public TransactionsRecyclerAdapter(ArrayList<Transaction> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
        return new SingleItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleItemRowHolder holder, int position) {
        final Transaction itemModel = mDataList.get(position);
        holder.list_item_transaction_date_tv.setText(itemModel.getTimereceived() + "");
        holder.list_item_transaction_address_tv.setText(itemModel.getAddress());
        holder.list_item_transaction_type_tv.setText(itemModel.getCategory());
        holder.list_item_transaction_amount_tv.setText(itemModel.getAmount() + "");
        holder.list_item_transaction_transaction_id_tv.setText(itemModel.getTxid());
        holder.list_item_transaction_confirmation_tv.setText(itemModel.getConfirmations() + "");
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

        private TextView list_item_transaction_date_tv;
        private TextView list_item_transaction_address_tv;
        private TextView list_item_transaction_type_tv;
        private TextView list_item_transaction_amount_tv;
        private TextView list_item_transaction_transaction_id_tv;
        private TextView list_item_transaction_confirmation_tv;

        SingleItemRowHolder(View itemView) {
            super(itemView);
            this.list_item_transaction_date_tv = itemView.findViewById(R.id.list_item_transaction_date_tv);
            this.list_item_transaction_address_tv = itemView.findViewById(R.id.list_item_transaction_address_tv);
            this.list_item_transaction_type_tv = itemView.findViewById(R.id.list_item_transaction_type_tv);
            this.list_item_transaction_amount_tv = itemView.findViewById(R.id.list_item_transaction_amount_tv);
            this.list_item_transaction_transaction_id_tv = itemView.findViewById(R.id.list_item_transaction_transaction_id_tv);
            this.list_item_transaction_confirmation_tv = itemView.findViewById(R.id.list_item_transaction_confirmation_tv);
        }
    }
}
