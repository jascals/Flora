package com.jascal.flora.net.bean.tc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ihave4cat
 * @describe 图虫api
 * @data on 2018/11/16 4:19 PM
 * @email jascal@163.com
 * */
public class Image implements Parcelable {
    private int img_id;
    private int user_id;
    private String title;
    private String excerpt;
    private int width;
    private int height;
    private String description;

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Image{" +
                "img_id=" + img_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.img_id);
        dest.writeInt(this.user_id);
        dest.writeString(this.title);
        dest.writeString(this.excerpt);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.description);
    }

    public Image() {

    }

    protected Image(Parcel in) {
        this.img_id = in.readInt();
        this.user_id = in.readInt();
        this.title = in.readString();
        this.excerpt = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
