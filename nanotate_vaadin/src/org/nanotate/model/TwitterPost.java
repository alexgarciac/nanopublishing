package org.nanotate.model;

public class TwitterPost extends TwitterPostKey {
    private String twitter_post_url;

    private Boolean automatic_post;

    public String getTwitter_post_url() {
        return twitter_post_url;
    }

    public void setTwitter_post_url(String twitter_post_url) {
        this.twitter_post_url = twitter_post_url;
    }

    public Boolean getAutomatic_post() {
        return automatic_post;
    }

    public void setAutomatic_post(Boolean automatic_post) {
        this.automatic_post = automatic_post;
    }
}