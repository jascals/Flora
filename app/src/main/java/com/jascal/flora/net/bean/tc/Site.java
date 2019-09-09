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
public class Site implements Parcelable {

    /**
     * site_id : 303555
     * type : user
     * name : 史小千
     * domain : shijunqian.tuchong.com
     * description : 资深创意摄影师
     * followers : 2781
     * url : https://shijunqian.tuchong.com/
     * icon : https://lf1-tccdn-tos.pstatp.com/obj/tuchong-avatar/l_303555_5
     * is_bind_everphoto : false
     * has_everphoto_note : true
     * verifications : 1
     * verification_list : [{"verification_type":13,"verification_reason":"资深创意摄影师"}]
     * verified : true
     * verified_type : 13
     * verified_reason : 资深创意摄影师
     * is_following : false
     */

    private String site_id;
    private String type;
    private String name;
    private String domain;
    private String description;
    private int followers;
    private String url;
    private String icon;
    private boolean is_bind_everphoto;
    private boolean has_everphoto_note;
    private int verifications;
    private boolean verified;
    private int verified_type;
    private String verified_reason;
    private boolean is_following;
    private List<Verification> verification_list;

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isIs_bind_everphoto() {
        return is_bind_everphoto;
    }

    public void setIs_bind_everphoto(boolean is_bind_everphoto) {
        this.is_bind_everphoto = is_bind_everphoto;
    }

    public boolean isHas_everphoto_note() {
        return has_everphoto_note;
    }

    public void setHas_everphoto_note(boolean has_everphoto_note) {
        this.has_everphoto_note = has_everphoto_note;
    }

    public int getVerifications() {
        return verifications;
    }

    public void setVerifications(int verifications) {
        this.verifications = verifications;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getVerified_type() {
        return verified_type;
    }

    public void setVerified_type(int verified_type) {
        this.verified_type = verified_type;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public boolean isIs_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public List<Verification> getVerification_list() {
        return verification_list;
    }

    public void setVerification_list(List<Verification> verification_list) {
        this.verification_list = verification_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site_id);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.domain);
        dest.writeString(this.description);
        dest.writeInt(this.followers);
        dest.writeString(this.url);
        dest.writeString(this.icon);
        dest.writeByte(this.is_bind_everphoto ? (byte) 1 : (byte) 0);
        dest.writeByte(this.has_everphoto_note ? (byte) 1 : (byte) 0);
        dest.writeInt(this.verifications);
        dest.writeByte(this.verified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.verified_type);
        dest.writeString(this.verified_reason);
        dest.writeByte(this.is_following ? (byte) 1 : (byte) 0);
        dest.writeList(this.verification_list);
    }

    public Site() {
    }

    protected Site(Parcel in) {
        this.site_id = in.readString();
        this.type = in.readString();
        this.name = in.readString();
        this.domain = in.readString();
        this.description = in.readString();
        this.followers = in.readInt();
        this.url = in.readString();
        this.icon = in.readString();
        this.is_bind_everphoto = in.readByte() != 0;
        this.has_everphoto_note = in.readByte() != 0;
        this.verifications = in.readInt();
        this.verified = in.readByte() != 0;
        this.verified_type = in.readInt();
        this.verified_reason = in.readString();
        this.is_following = in.readByte() != 0;
        this.verification_list = new ArrayList<Verification>();
        in.readList(this.verification_list, Verification.class.getClassLoader());
    }

    public static final Parcelable.Creator<Site> CREATOR = new Parcelable.Creator<Site>() {
        @Override
        public Site createFromParcel(Parcel source) {
            return new Site(source);
        }

        @Override
        public Site[] newArray(int size) {
            return new Site[size];
        }
    };
}
