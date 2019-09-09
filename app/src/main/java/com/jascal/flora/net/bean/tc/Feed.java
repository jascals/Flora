package com.jascal.flora.net.bean.tc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ihave4cat
 * @describe 图虫api
 * @data on 2018/11/16 4:19 PM
 * @email jascal@163.com
 * */
public class Feed implements Parcelable {

    /**
     * post_id : 49647264
     * type : multi-photo
     * url : https://shijunqian.tuchong.com/49647264/
     * site_id : 303555
     * author_id : 303555
     * published_at : 2019-08-18 08:09:47
     * passed_time : 08月18日
     * excerpt : 人与星球
     * favorites : 79
     * comments : 3
     * rewardable : true
     * parent_comments : 3
     * rewards : 0
     * views : 2299
     * collected : false
     * shares : 2
     * recommend : true
     * delete : false
     * update : false
     * content : 人与星球
     * title :
     * image_count : 1
     * images : []
     * title_image : null
     * tags : ["我要上开屏","发现自然之美","荒地","地质学","沙","一个人","黏土","手机摄影","沙质的","发热的","土壤","背影","沙漠","雅丹","干旱","干"]
     * event_tags : ["我要上开屏","发现自然之美"]
     * favorite_list_prefix : []
     * reward_list_prefix : []
     * comment_list_prefix : []
     * data_type : post
     * created_at : 2019-08-18 08:09:47
     * sites : []
     * site : {}
     * recom_type :
     * rqt_id : ff77c2a844573ecc8bccb0745d0dfc06
     * is_favorite : false
     */

    private int post_id;
    private String type;
    private String url;
    private String site_id;
    private String author_id;
    private String published_at;
    private String passed_time;
    private String excerpt;
    private int favorites;
    private int comments;
    private boolean rewardable;
    private String parent_comments;
    private String rewards;
    private int views;
    private boolean collected;
    private int shares;
    private boolean recommend;
    private boolean delete;
    private boolean update;
    private String content;
    private String title;
    private int image_count;
    private String title_image;
    private String data_type;
    private String created_at;
    private Site site;
    private String recom_type;
    private String rqt_id;
    private boolean is_favorite;
    private List<Image> images;
    private List<String> tags;
    private List<String> event_tags;
    private List<Object> favorite_list_prefix;
    private List<Object> reward_list_prefix;
    private List<Object> comment_list_prefix;
    private List<Site> sites;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getPassed_time() {
        return passed_time;
    }

    public void setPassed_time(String passed_time) {
        this.passed_time = passed_time;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public boolean isRewardable() {
        return rewardable;
    }

    public void setRewardable(boolean rewardable) {
        this.rewardable = rewardable;
    }

    public String getParent_comments() {
        return parent_comments;
    }

    public void setParent_comments(String parent_comments) {
        this.parent_comments = parent_comments;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_count() {
        return image_count;
    }

    public void setImage_count(int image_count) {
        this.image_count = image_count;
    }

    public String getTitle_image() {
        return title_image;
    }

    public void setTitle_image(String title_image) {
        this.title_image = title_image;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getRecom_type() {
        return recom_type;
    }

    public void setRecom_type(String recom_type) {
        this.recom_type = recom_type;
    }

    public String getRqt_id() {
        return rqt_id;
    }

    public void setRqt_id(String rqt_id) {
        this.rqt_id = rqt_id;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getEvent_tags() {
        return event_tags;
    }

    public void setEvent_tags(List<String> event_tags) {
        this.event_tags = event_tags;
    }

    public List<Object> getFavorite_list_prefix() {
        return favorite_list_prefix;
    }

    public void setFavorite_list_prefix(List<Object> favorite_list_prefix) {
        this.favorite_list_prefix = favorite_list_prefix;
    }

    public List<Object> getReward_list_prefix() {
        return reward_list_prefix;
    }

    public void setReward_list_prefix(List<Object> reward_list_prefix) {
        this.reward_list_prefix = reward_list_prefix;
    }

    public List<Object> getComment_list_prefix() {
        return comment_list_prefix;
    }

    public void setComment_list_prefix(List<Object> comment_list_prefix) {
        this.comment_list_prefix = comment_list_prefix;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.post_id);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.site_id);
        dest.writeString(this.author_id);
        dest.writeString(this.published_at);
        dest.writeString(this.passed_time);
        dest.writeString(this.excerpt);
        dest.writeInt(this.favorites);
        dest.writeInt(this.comments);
        dest.writeByte(this.rewardable ? (byte) 1 : (byte) 0);
        dest.writeString(this.parent_comments);
        dest.writeString(this.rewards);
        dest.writeInt(this.views);
        dest.writeByte(this.collected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.shares);
        dest.writeByte(this.recommend ? (byte) 1 : (byte) 0);
        dest.writeByte(this.delete ? (byte) 1 : (byte) 0);
        dest.writeByte(this.update ? (byte) 1 : (byte) 0);
        dest.writeString(this.content);
        dest.writeString(this.title);
        dest.writeInt(this.image_count);
        dest.writeString(this.title_image);
        dest.writeString(this.data_type);
        dest.writeString(this.created_at);
        dest.writeParcelable(this.site, flags);
        dest.writeString(this.recom_type);
        dest.writeString(this.rqt_id);
        dest.writeByte(this.is_favorite ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.images);
        dest.writeStringList(this.tags);
        dest.writeStringList(this.event_tags);
        dest.writeList(this.favorite_list_prefix);
        dest.writeList(this.reward_list_prefix);
        dest.writeList(this.comment_list_prefix);
        dest.writeTypedList(this.sites);
    }

    public Feed() {
    }

    protected Feed(Parcel in) {
        this.post_id = in.readInt();
        this.type = in.readString();
        this.url = in.readString();
        this.site_id = in.readString();
        this.author_id = in.readString();
        this.published_at = in.readString();
        this.passed_time = in.readString();
        this.excerpt = in.readString();
        this.favorites = in.readInt();
        this.comments = in.readInt();
        this.rewardable = in.readByte() != 0;
        this.parent_comments = in.readString();
        this.rewards = in.readString();
        this.views = in.readInt();
        this.collected = in.readByte() != 0;
        this.shares = in.readInt();
        this.recommend = in.readByte() != 0;
        this.delete = in.readByte() != 0;
        this.update = in.readByte() != 0;
        this.content = in.readString();
        this.title = in.readString();
        this.image_count = in.readInt();
        this.title_image = in.readString();
        this.data_type = in.readString();
        this.created_at = in.readString();
        this.site = in.readParcelable(Site.class.getClassLoader());
        this.recom_type = in.readString();
        this.rqt_id = in.readString();
        this.is_favorite = in.readByte() != 0;
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.tags = in.createStringArrayList();
        this.event_tags = in.createStringArrayList();
        this.favorite_list_prefix = new ArrayList<Object>();
        in.readList(this.favorite_list_prefix, Object.class.getClassLoader());
        this.reward_list_prefix = new ArrayList<Object>();
        in.readList(this.reward_list_prefix, Object.class.getClassLoader());
        this.comment_list_prefix = new ArrayList<Object>();
        in.readList(this.comment_list_prefix, Object.class.getClassLoader());
        this.sites = in.createTypedArrayList(Site.CREATOR);
    }

    public static final Parcelable.Creator<Feed> CREATOR = new Parcelable.Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
}
