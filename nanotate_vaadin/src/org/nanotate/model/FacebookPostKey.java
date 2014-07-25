package org.nanotate.model;

public class FacebookPostKey {
    private Integer facebook_post_id;

    private String annotation_id;

    public Integer getFacebook_post_id() {
        return facebook_post_id;
    }

    public void setFacebook_post_id(Integer facebook_post_id) {
        this.facebook_post_id = facebook_post_id;
    }

    public String getAnnotation_id() {
        return annotation_id;
    }

    public void setAnnotation_id(String annotation_id) {
        this.annotation_id = annotation_id;
    }
}