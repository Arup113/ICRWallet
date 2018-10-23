
package com.nurdcoder.android.icr_wallet.data.local.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = new ArrayList<Transaction>();
    @SerializedName("recordcount")
    @Expose
    private long recordcount;
    @SerializedName("Message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param message
     * @param transactions
     * @param token
     * @param recordcount
     */
    public ApiResponse(String token, List<Transaction> transactions, long recordcount, String message) {
        super();
        this.token = token;
        this.transactions = transactions;
        this.recordcount = recordcount;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(long recordcount) {
        this.recordcount = recordcount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("transactions", transactions).append("recordcount", recordcount).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(transactions).append(token).append(recordcount).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ApiResponse) == false) {
            return false;
        }
        ApiResponse rhs = ((ApiResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(transactions, rhs.transactions).append(token, rhs.token).append(recordcount, rhs.recordcount).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeList(this.transactions);
        dest.writeLong(this.recordcount);
        dest.writeString(this.message);
    }

    protected ApiResponse(Parcel in) {
        this.token = in.readString();
        this.transactions = new ArrayList<Transaction>();
        in.readList(this.transactions, Transaction.class.getClassLoader());
        this.recordcount = in.readLong();
        this.message = in.readString();
    }

    public static final Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {
        @Override
        public ApiResponse createFromParcel(Parcel source) {
            return new ApiResponse(source);
        }

        @Override
        public ApiResponse[] newArray(int size) {
            return new ApiResponse[size];
        }
    };
}
