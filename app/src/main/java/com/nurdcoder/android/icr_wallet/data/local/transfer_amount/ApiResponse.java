package com.nurdcoder.android.icr_wallet.data.local.transfer_amount;

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
    @SerializedName("transactionid")
    @Expose
    private String transactionid;
    @SerializedName("Message")
    @Expose
    private String message;

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
        this.transactionid = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param message
     * @param token
     * @param transactionid
     */
    public ApiResponse(String token, String transactionid, String message) {
        super();
        this.token = token;
        this.transactionid = transactionid;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("transactionid", transactionid).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(token).append(transactionid).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(token, rhs.token).append(transactionid, rhs.transactionid).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(token);
        dest.writeValue(transactionid);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}