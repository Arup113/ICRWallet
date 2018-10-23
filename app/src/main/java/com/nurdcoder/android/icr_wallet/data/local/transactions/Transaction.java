
package com.nurdcoder.android.icr_wallet.data.local.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Transaction implements Parcelable {

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("amount")
    @Expose
    private long amount;
    @SerializedName("confirmations")
    @Expose
    private long confirmations;
    @SerializedName("blockhash")
    @Expose
    private String blockhash;
    @SerializedName("blockindex")
    @Expose
    private long blockindex;
    @SerializedName("blocktime")
    @Expose
    private long blocktime;
    @SerializedName("txid")
    @Expose
    private String txid;
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("timereceived")
    @Expose
    private long timereceived;

    /**
     * No args constructor for use in serialization
     */
    public Transaction() {
    }

    /**
     * @param amount
     * @param blockindex
     * @param time
     * @param category
     * @param confirmations
     * @param timereceived
     * @param address
     * @param txid
     * @param blockhash
     * @param account
     * @param blocktime
     */
    public Transaction(String account, String address, String category, long amount, long confirmations, String blockhash, long blockindex, long blocktime, String txid, long time, long timereceived) {
        super();
        this.account = account;
        this.address = address;
        this.category = category;
        this.amount = amount;
        this.confirmations = confirmations;
        this.blockhash = blockhash;
        this.blockindex = blockindex;
        this.blocktime = blocktime;
        this.txid = txid;
        this.time = time;
        this.timereceived = timereceived;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public long getBlockindex() {
        return blockindex;
    }

    public void setBlockindex(long blockindex) {
        this.blockindex = blockindex;
    }

    public long getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(long blocktime) {
        this.blocktime = blocktime;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimereceived() {
        return timereceived;
    }

    public void setTimereceived(long timereceived) {
        this.timereceived = timereceived;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("account", account).append("address", address).append("category", category).append("amount", amount).append("confirmations", confirmations).append("blockhash", blockhash).append("blockindex", blockindex).append("blocktime", blocktime).append("txid", txid).append("time", time).append("timereceived", timereceived).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(amount).append(blockindex).append(time).append(category).append(confirmations).append(timereceived).append(address).append(txid).append(blockhash).append(account).append(blocktime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Transaction) == false) {
            return false;
        }
        Transaction rhs = ((Transaction) other);
        return new EqualsBuilder().append(amount, rhs.amount).append(blockindex, rhs.blockindex).append(time, rhs.time).append(category, rhs.category).append(confirmations, rhs.confirmations).append(timereceived, rhs.timereceived).append(address, rhs.address).append(txid, rhs.txid).append(blockhash, rhs.blockhash).append(account, rhs.account).append(blocktime, rhs.blocktime).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.account);
        dest.writeString(this.address);
        dest.writeString(this.category);
        dest.writeLong(this.amount);
        dest.writeLong(this.confirmations);
        dest.writeString(this.blockhash);
        dest.writeLong(this.blockindex);
        dest.writeLong(this.blocktime);
        dest.writeString(this.txid);
        dest.writeLong(this.time);
        dest.writeLong(this.timereceived);
    }

    protected Transaction(Parcel in) {
        this.account = in.readString();
        this.address = in.readString();
        this.category = in.readString();
        this.amount = in.readLong();
        this.confirmations = in.readLong();
        this.blockhash = in.readString();
        this.blockindex = in.readLong();
        this.blocktime = in.readLong();
        this.txid = in.readString();
        this.time = in.readLong();
        this.timereceived = in.readLong();
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
