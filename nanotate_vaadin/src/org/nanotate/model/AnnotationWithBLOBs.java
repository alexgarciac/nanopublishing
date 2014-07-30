package org.nanotate.model;

public class AnnotationWithBLOBs extends Annotation {

	private String text;
	private String quote;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}}