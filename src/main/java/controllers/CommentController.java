package controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pojo.Comment;
import service.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	
	@GetMapping(value = "/bug/{bugId}/comment")
	public List<Comment> getBugComments(@PathVariable UUID bugId){
		return commentService.getComments(bugId);
	}
	
	@PostMapping(value = "/bug/{bugId}/comment")
	public UUID addBugComment(@PathVariable UUID bugId, @RequestBody String commentText){
		return commentService.addComment(bugId, commentText);
	}
	
	@GetMapping(value = "/bug/{bugId}/comment/{commentId}")
	public Comment getBugCommentById(@PathVariable UUID bugId,@PathVariable UUID commentId){
		return commentService.getCommentById(bugId, commentId);
	}
	
	@DeleteMapping(value = "/bug/{bugId}/comment/{commentId}")
	public void deleteBugComment(@PathVariable UUID bugId,@PathVariable UUID commentId) {
		commentService.deleteComment(bugId, commentId);
	}
	
}
