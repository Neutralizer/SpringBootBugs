package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

}
