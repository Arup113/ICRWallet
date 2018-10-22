package com.nurdcoder.android.icr_wallet.ui.transaction;

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
