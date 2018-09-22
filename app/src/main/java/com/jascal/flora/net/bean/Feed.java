package com.jascal.flora.net.bean;

import java.util.List;

public class Feed {
    private int post_id;
    private String type;
    private String url;
    private String site_id;
    private String author_id;
    private String published_at;
    private String excerpt;
    private int favorites;
    private int comments;
    private boolean delete;
    private boolean update;
    private String content;
    private String title;
    private int image_count;
    private String data_type;
    private String created_at;
    private Site site;
    private String recom_type;
    private String rqt_id;
    private boolean is_favorite;
    private List<Image> images;
    private List<String> tags;
    private List<String> event_tags;
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

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "post_id=" + post_id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", site_id='" + site_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", published_at='" + published_at + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", favorites=" + favorites +
                ", comments=" + comments +
                ", delete=" + delete +
                ", update=" + update +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", image_count=" + image_count +
                ", data_type='" + data_type + '\'' +
                ", created_at='" + created_at + '\'' +
                ", site=" + site +
                ", recom_type='" + recom_type + '\'' +
                ", rqt_id='" + rqt_id + '\'' +
                ", is_favorite=" + is_favorite +
                ", images=" + images +
                ", tags=" + tags +
                ", event_tags=" + event_tags +
                ", sites=" + sites +
                '}';
    }
}
