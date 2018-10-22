package com.nurdcoder.android.icr_wallet.ui.my_addresses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.local.my_addresses.Address;

import java.util.ArrayList;

public class MyAddressesRecyclerAdapter extends RecyclerView.Adapter<MyAddressesRecyclerAdapter.SingleItemRowHolder> {

    private ArrayList<Address> mDataList;
    private Context mContext;

    public MyAddressesRecyclerAdapter(ArrayList<Address> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
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

        SingleItemRowHolder(View itemView) {
            super(itemView);
            this.list_item_my_addresses_label_tv = itemView.findViewById(R.id.list_item_my_addresses_label_tv);
            this.list_item_my_addresses_address_tv = itemView.findViewById(R.id.list_item_my_addresses_address_tv);
        }
    }
}
