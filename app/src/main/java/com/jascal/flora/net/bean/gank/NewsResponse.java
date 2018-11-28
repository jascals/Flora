package com.jascal.flora.net.bean.gank;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 2:50 PM
 * @email jascal@163.com
 */
public class NewsResponse implements Parcelable {
    /**
     * category : ["App","拓展资源","iOS","前端","Android","福利","休息视频"]
     * error : false
     * results : {"Android":[],"App":[],"iOS":[],"休息视频":[],"拓展资源":[],"瞎推荐":[],"福利":[]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean implements Parcelable {
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "Android=" + Android +
                    ", App=" + App +
                    ", iOS=" + iOS +
                    ", Video=" + Video +
                    ", Expand=" + Expand +
                    ", Things=" + Things +
                    ", Beauty=" + Beauty +
                    '}';
        }

        private List<News> Android;
        private List<News> App;
        private List<News> iOS;

        @SerializedName("休息视频")
        private List<News> Video;

        @SerializedName("拓展资源")
        private List<News> Expand;

        @SerializedName("前端")
        private List<News> Things;

        @SerializedName("福利")
        private List<News> Beauty;

        public List<News> getAndroid() {
            return Android;
        }

        public void setAndroid(List<News> Android) {
            this.Android = Android;
        }

        public List<News> getApp() {
            return App;
        }

        public void setApp(List<News> App) {
            this.App = App;
        }

        public List<News> getIOS() {
            return iOS;
        }

        public void setIOS(List<News> iOS) {
            this.iOS = iOS;
        }

        public List<News> getVideo() {
            return Video;
        }

        public void setVideo(List<News> Video) {
            this.Video = Video;
        }

        public List<News> getExpand() {
            return Expand;
        }

        public void setExpand(List<News> Expand) {
            this.Expand = Expand;
        }

        public List<News> getThings() {
            return Things;
        }

        public void setThings(List<News> Things) {
            this.Things = Things;
        }

        public List<News> getBeauty() {
            return Beauty;
        }

        public void setBeauty(List<News> Beauty) {
            this.Beauty = Beauty;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.Android);
            dest.writeTypedList(this.App);
            dest.writeTypedList(this.iOS);
            dest.writeTypedList(this.Video);
            dest.writeTypedList(this.Expand);
            dest.writeTypedList(this.Things);
            dest.writeTypedList(this.Beauty);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.Android = in.createTypedArrayList(News.CREATOR);
            this.App = in.createTypedArrayList(News.CREATOR);
            this.iOS = in.createTypedArrayList(News.CREATOR);
            this.Video = in.createTypedArrayList(News.CREATOR);
            this.Expand = in.createTypedArrayList(News.CREATOR);
            this.Things = in.createTypedArrayList(News.CREATOR);
            this.Beauty = in.createTypedArrayList(News.CREATOR);
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.results, flags);
        dest.writeStringList(this.category);
    }

    public NewsResponse() {
    }

    protected NewsResponse(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.readParcelable(ResultsBean.class.getClassLoader());
        this.category = in.createStringArrayList();
    }

    public static final Parcelable.Creator<NewsResponse> CREATOR = new Parcelable.Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel source) {
            return new NewsResponse(source);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };

    @Override
    public String toString() {
        return "NewsResponse{" +
                "error=" + error +
                ", results=" + results +
                ", category=" + category +
                '}';
    }
}
