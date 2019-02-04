package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import controllers.BugRepository;
import pojo.Bug;
import pojo.Comment;
import service.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class TestCommentSerivce {

	private CommentService commentService;

	@Mock
	private BugRepository bugRepository;

	@Before
	public void init() {
		commentService = new CommentService();
		ReflectionTestUtils.setField(commentService, "bugRepository", bugRepository);
	}

	@Test
	public void testCommentListNotNull() {
		ArrayList<Comment> list = new ArrayList<>();
		list.add(Comment.builder().id(UUID.randomUUID()).text("comment text").build());
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").comments(list).build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		List<Comment> comments = commentService.getComments(bug.getId());
		Assert.assertFalse(comments.isEmpty());
	}

	@Test
	public void testCommentListExpectedSize() {
		ArrayList<Comment> list = new ArrayList<>();
		list.add(Comment.builder().id(UUID.randomUUID()).text("comment text1").build());
		list.add(Comment.builder().id(UUID.randomUUID()).text("comment text2").build());
		list.add(Comment.builder().id(UUID.randomUUID()).text("comment text3").build());

		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss1").comments(list).build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		List<Comment> comments = commentService.getComments(bug.getId());
		assertEquals(3, comments.size());
	}

	@Test
	public void testAddCommentReturnedNotNull() {
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		UUID newCommentUUID = commentService.addComment(bug.getId(), "new comment");

		assertNotNull(newCommentUUID);
	}

	@Test
	public void testIsSpecificCommentAdded() {
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		commentService.addComment(bug.getId(), "new comment");

		assertEquals("new comment", bug.getComments().get(0).getText());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testOnlyOneCommentAdded() {
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		commentService.addComment(bug.getId(), "new comment");

		bug.getComments().get(1).getText();
	}

	@Test
	public void testSpecificNumberCommentsAdded() {
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		commentService.addComment(bug.getId(), "new comment1");
		commentService.addComment(bug.getId(), "new comment2");
		commentService.addComment(bug.getId(), "new comment3");

		assertEquals(3, bug.getComments().size());
	}

	@Test
	public void testGetCommentByIdManyCommentsPresent() {
		ArrayList<Comment> list = new ArrayList<>();
		Comment comment1 = Comment.builder().id(UUID.randomUUID()).text("comment text1").build();
		Comment comment2 = Comment.builder().id(UUID.randomUUID()).text("comment text2").build();
		Comment comment3 = Comment.builder().id(UUID.randomUUID()).text("comment text3").build();
		list.add(comment1);
		list.add(comment2);
		list.add(comment3);

		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").comments(list).build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		Comment commentResult = commentService.getCommentById(bug.getId(), comment2.getId());

		assertEquals(comment2.getId(), commentResult.getId());

	}

	@Test
	public void testGetCommentByIdNotFound() {

		ArrayList<Comment> list = new ArrayList<>();
		Comment comment1 = Comment.builder().id(UUID.randomUUID()).text("comment text1").build();
		Comment comment2 = Comment.builder().id(UUID.randomUUID()).text("comment text2").build();
		Comment comment3 = Comment.builder().id(UUID.randomUUID()).text("comment text3").build();
		list.add(comment1);
		list.add(comment2);
		list.add(comment3);

		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").comments(list).build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		Comment commentResult = commentService.getCommentById(bug.getId(), UUID.randomUUID());

		assertNull(commentResult);

	}

	@Test
	public void testDeleteComment() {

		ArrayList<Comment> list = new ArrayList<>();
		Comment comment1 = Comment.builder().id(UUID.randomUUID()).text("comment text1").build();
		Comment comment2 = Comment.builder().id(UUID.randomUUID()).text("comment text2").build();
		Comment comment3 = Comment.builder().id(UUID.randomUUID()).text("comment text3").build();
		list.add(comment1);
		list.add(comment2);
		list.add(comment3);
		Bug bug = Bug.builder().id(UUID.randomUUID()).title("sss").comments(list).build();

		Mockito.when(bugRepository.getById(bug.getId())).thenReturn(bug);

		commentService.deleteComment(bug.getId(), comment2.getId());

		assertEquals(2, bug.getComments().size());
	}

}
