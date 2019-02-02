package service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controllers.BugRepository;
import pojo.Comment;

@Service
public class CommentService {

	@Autowired
	private BugRepository bugRepository;

	public List<Comment> getComments(UUID bugId) {
		return bugRepository.getById(bugId).getComments();
	}

	public UUID addComment(UUID bugId, String commentText) {
		Comment comment = Comment.builder().id(UUID.randomUUID()).text(commentText).build();
		bugRepository.getById(bugId).getComments().add(comment);
		return comment.getId();
	}

	public Comment getCommentById(UUID bugId, UUID commentId) {
		List<Comment> comments = bugRepository.getById(bugId).getComments();
		for (Comment comment : comments) {
			if (comment.getId().equals(commentId)) {
				return comment;
			}
		}
		return null;
	}

	public void deleteComment(UUID bugId, UUID commentId) {
		List<Comment> comments = bugRepository.getById(bugId).getComments();
		for (Comment comment : comments) {
			if (comment.getId().equals(commentId)) {
				comments.remove(comment);
				bugRepository.getById(bugId).setComments(comments);
			}
		}
	}

}
