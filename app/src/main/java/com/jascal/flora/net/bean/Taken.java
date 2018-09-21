package com.jascal.flora.net.bean;

public class Taken {

    /**
     * access_token : cd27eea87bb33ece7e3ce6b6602a00152fa910b51b2607237ff5afb35c510174
     * token_type : Bearer
     * scope : public
     * created_at : 1537522684
     */

    private String access_token;
    private String token_type;
    private String scope;
    private int created_at;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Taken{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", scope='" + scope + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
