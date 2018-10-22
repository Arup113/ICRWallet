
package com.nurdcoder.android.icr_wallet.data.local.my_addresses;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiResponse {

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
     * 
     */
    public ApiResponse() {
    }

    /**
     * 
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

}
