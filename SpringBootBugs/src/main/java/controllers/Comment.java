package controllers;

import java.util.UUID;

public class Comment {

	private String id;
	private String text;

	public Comment() {
		// default
	}

	public Comment(String text) {
		this.id = UUID.randomUUID().toString();
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
