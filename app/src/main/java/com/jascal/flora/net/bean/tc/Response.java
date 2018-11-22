package com.jascal.flora.net.bean.tc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author ihave4cat
 * @describe 图虫api
 * @data on 2018/11/16 4:19 PM
 * @email jascal@163.com
 * */
public class Response implements Parcelable {
    private boolean is_history;
    private int counts;
    private String message;
    private boolean more;
    private String result;
    private List<Feed> feedList;

    public boolean isIs_history() {
        return is_history;
    }

    public void setIs_history(boolean is_history) {
        this.is_history = is_history;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public String toString() {
        return "Response{" +
                "is_history=" + is_history +
                ", counts=" + counts +
                ", message='" + message + '\'' +
                ", more=" + more +
                ", result='" + result + '\'' +
                ", feedList=" + feedList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.is_history ? (byte) 1 : (byte) 0);
        dest.writeInt(this.counts);
        dest.writeString(this.message);
        dest.writeByte(this.more ? (byte) 1 : (byte) 0);
        dest.writeString(this.result);
        dest.writeTypedList(this.feedList);
    }

    public Response() {
    }

    protected Response(Parcel in) {
        this.is_history = in.readByte() != 0;
        this.counts = in.readInt();
        this.message = in.readString();
        this.more = in.readByte() != 0;
        this.result = in.readString();
        this.feedList = in.createTypedArrayList(Feed.CREATOR);
    }

    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
}
