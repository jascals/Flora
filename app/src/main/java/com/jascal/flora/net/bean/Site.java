package com.jascal.flora.net.bean;

import java.util.List;

public class Site {
    private String site_id;
    private String type;
    private String name;
    private String domain;
    private String description;
    private int followers;
    private String url;
    private String icon;
    private boolean verified;
    private int verified_type;
    private String verified_reason;
    private int verifications;
    private List<?> verification_list;

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

    public int getVerifications() {
        return verifications;
    }

    public void setVerifications(int verifications) {
        this.verifications = verifications;
    }

    public List<?> getVerification_list() {
        return verification_list;
    }

    public void setVerification_list(List<?> verification_list) {
        this.verification_list = verification_list;
    }

    @Override
    public String toString() {
        return "Site{" +
                "site_id='" + site_id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", description='" + description + '\'' +
                ", followers=" + followers +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", verified=" + verified +
                ", verified_type=" + verified_type +
                ", verified_reason='" + verified_reason + '\'' +
                ", verifications=" + verifications +
                ", verification_list=" + verification_list +
                '}';
    }
}
