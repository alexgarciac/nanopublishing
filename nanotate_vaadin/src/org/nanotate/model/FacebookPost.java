package org.nanotate.model;

public class FacebookPost extends FacebookPostKey {
    private String facebook_post_url;

    private Boolean automatic_post;

    public String getFacebook_post_url() {
        return facebook_post_url;
    }

    public void setFacebook_post_url(String facebook_post_url) {
        this.facebook_post_url = facebook_post_url;
    }

    public Boolean getAutomatic_post() {
        return automatic_post;
    }

    public void setAutomatic_post(Boolean automatic_post) {
        this.automatic_post = automatic_post;
    }
}