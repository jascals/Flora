package com.jascal.flora.net.bean.tc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ihave4cat
 * @describe 图虫api
 * @data on 2018/11/16 4:19 PM
 * @email jascal@163.com
 * */
public class Verification implements Parcelable {
    private int verification;
    private String verification_reason;

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }

    public String getVerification_reason() {
        return verification_reason;
    }

    public void setVerification_reason(String verification_reason) {
        this.verification_reason = verification_reason;
    }


    @Override
    public String toString() {
        return "Verification{" +
                "verification=" + verification +
                ", verification_reason='" + verification_reason + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.verification);
        dest.writeString(this.verification_reason);
    }

    public Verification() {
    }

    protected Verification(Parcel in) {
        this.verification = in.readInt();
        this.verification_reason = in.readString();
    }

    public static final Parcelable.Creator<Verification> CREATOR = new Parcelable.Creator<Verification>() {
        @Override
        public Verification createFromParcel(Parcel source) {
            return new Verification(source);
        }

        @Override
        public Verification[] newArray(int size) {
            return new Verification[size];
        }
    };
}
