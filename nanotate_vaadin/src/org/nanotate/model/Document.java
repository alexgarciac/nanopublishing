package org.nanotate.model;

import java.util.Date;

public class Document {
    private String doc_uuid;

    private String doi;

    private String title;

    private String full_citation;

    private String first_author;

    private Integer year;

    private String uploaded_by;

    private Date upload_date;

    public String getDoc_uuid() {
        return doc_uuid;
    }

    public void setDoc_uuid(String doc_uuid) {
        this.doc_uuid = doc_uuid;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFull_citation() {
        return full_citation;
    }

    public void setFull_citation(String full_citation) {
        this.full_citation = full_citation;
    }

    public String getFirst_author() {
        return first_author;
    }

    public void setFirst_author(String first_author) {
        this.first_author = first_author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }
}