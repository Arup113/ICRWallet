package com.nurdcoder.android.icr_wallet.data.local.balance;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiResponse implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("balance")
    @Expose
    private long balance;
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
     * @param balance
     * @param token
     */
    public ApiResponse(String token, long balance, String message) {
        super();
        this.token = token;
        this.balance = balance;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("balance", balance).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(balance).append(token).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(balance, rhs.balance).append(token, rhs.token).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeLong(this.balance);
        dest.writeString(this.message);
    }

    protected ApiResponse(Parcel in) {
        this.token = in.readString();
        this.balance = in.readLong();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ApiResponse> CREATOR = new Parcelable.Creator<ApiResponse>() {
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