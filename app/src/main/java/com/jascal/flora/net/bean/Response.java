package com.jascal.flora.net.bean;

import java.util.List;

public class Response {
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
}
