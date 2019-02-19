package com.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the Bug in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bug {

	@JsonIgnore
	private UUID id;
	private String title;
	private String description;
	private List<Comment> comments;

	public boolean addComment(Comment comment) {
		if (this.comments == null) {
			comments = new ArrayList<Comment>();
		}
		return this.comments.add(comment);
	}

}
