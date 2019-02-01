package controllers;

import java.util.List;
import java.util.UUID;

import controllers.Bug.BugBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	private UUID id;
	private String text;

//	public Comment(String text) {
//		this.id = UUID.randomUUID();
//		this.text = text;
//	}

}
