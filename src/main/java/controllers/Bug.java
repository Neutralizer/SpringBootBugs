package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bug {

	private String id;
	private String title;
	private String description;
	private List<Comment> comments = new ArrayList<>();

	public Bug() {
		// default
	}

	public Bug(String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
	}

	public Bug(String title, String description, Comment comment) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
		this.comments.add(comment);
	}
	
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public boolean addComment(Comment comment) {
		return this.comments.add(comment);
	}

}
