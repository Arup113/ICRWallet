
package com.nurdcoder.android.icr_wallet.data.local.my_addresses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("label")
    @Expose
    private String label;

    /**
     * No args constructor for use in serialization
     */
    public Address() {
    }

    /**
     * @param address
     * @param label
     */
    public Address(String address, String label) {
        super();
        this.address = address;
        this.label = label;
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
        return new ToStringBuilder(this).append("address", address).append("label", label).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(address).append(label).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(address, rhs.address).append(label, rhs.label).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.label);
    }

    protected Address(Parcel in) {
        this.address = in.readString();
        this.label = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
