
package com.nurdcoder.android.icr_wallet.data.local.my_addresses;

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
    @SerializedName("address")
    @Expose
    private List<Address> address = new ArrayList<Address>();
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
     * @param address
     * @param token
     */
    public ApiResponse(String token, List<Address> address, String message) {
        super();
        this.token = token;
        this.address = address;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("address", address).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(address).append(token).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(address, rhs.address).append(token, rhs.token).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeTypedList(this.address);
        dest.writeString(this.message);
    }

    protected ApiResponse(Parcel in) {
        this.token = in.readString();
        this.address = in.createTypedArrayList(Address.CREATOR);
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
