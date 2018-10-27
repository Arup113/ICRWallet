package com.nurdcoder.android.icr_wallet.data.local.home;

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
    private double balance;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("qrcode")
    @Expose
    private String qrcode;

    public final static Parcelable.Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {

        public ApiResponse createFromParcel(Parcel in) {
            return new ApiResponse(in);
        }

        public ApiResponse[] newArray(int size) {
            return (new ApiResponse[size]);
        }

    };

    protected ApiResponse(Parcel in) {
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.balance = ((double) in.readValue((double.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.qrcode = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param message
     * @param balance
     * @param address
     * @param token
     * @param qrcode
     */
    public ApiResponse(String token, double balance, String message, String address, String qrcode) {
        super();
        this.token = token;
        this.balance = balance;
        this.message = message;
        this.address = address;
        this.qrcode = qrcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("balance", balance).append("message", message).append("address", address).append("qrcode", qrcode).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(balance).append(address).append(token).append(qrcode).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(balance, rhs.balance).append(address, rhs.address).append(token, rhs.token).append(qrcode, rhs.qrcode).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(token);
        dest.writeValue(balance);
        dest.writeValue(message);
        dest.writeValue(address);
        dest.writeValue(qrcode);
    }

    public int describeContents() {
        return 0;
    }
}