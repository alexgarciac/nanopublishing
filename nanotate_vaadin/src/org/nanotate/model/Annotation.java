package org.nanotate.model;

import java.util.Date;

public class Annotation {

	private String annotation_id;
	private String annotator_schema_version;
	private Date created;
	private Date updated;
	private String uri;
	private String user;
	private String consumer;
	private Boolean status_completed;

	public String getAnnotation_id() {
		return annotation_id;
	}

	public void setAnnotation_id(String annotation_id) {
		this.annotation_id = annotation_id;
	}

	public String getAnnotator_schema_version() {
		return annotator_schema_version;
	}

	public void setAnnotator_schema_version(String annotator_schema_version) {
		this.annotator_schema_version = annotator_schema_version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public Boolean getStatus_completed() {
		return status_completed;
	}

	public void setStatus_completed(Boolean status_completed) {
		this.status_completed = status_completed;
	}}