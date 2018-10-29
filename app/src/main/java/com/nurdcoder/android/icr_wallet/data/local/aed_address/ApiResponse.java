package com.nurdcoder.android.icr_wallet.data.local.aed_address;

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
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("label")
    @Expose
    private String label;

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param message
     * @param address
     * @param token
     * @param label
     */
    public ApiResponse(String token, String message, String address, String label) {
        super();
        this.token = token;
        this.message = message;
        this.address = address;
        this.label = label;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("message", message).append("address", address).append("label", label).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(address).append(token).append(label).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(address, rhs.address).append(token, rhs.token).append(label, rhs.label).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.message);
        dest.writeString(this.address);
        dest.writeString(this.label);
    }

    protected ApiResponse(Parcel in) {
        this.token = in.readString();
        this.message = in.readString();
        this.address = in.readString();
        this.label = in.readString();
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