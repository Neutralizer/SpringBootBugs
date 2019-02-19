package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.controllers.BugRepository;
import com.pojo.Bug;
import com.service.BugService;

@RunWith(MockitoJUnitRunner.class)
public class TestBugService {

	private BugService bugService;

	@Mock
	private BugRepository bugRepository;

	@Before
	public void init() {
		bugService = new BugService();

		ReflectionTestUtils.setField(bugService, "bugRepository", bugRepository);
	}

	@Test
	public void testBugListIsNotNull() {
		Mockito.when(bugRepository.getAll()).thenReturn(Arrays.asList(new Bug()));

		List<Bug> bugs = bugService.getBugList();
		Assert.assertFalse(bugs.isEmpty());
	}

	@Test
	public void testBugListExpectedSize() {
		Mockito.when(bugRepository.getAll()).thenReturn(Arrays.asList(new Bug(), new Bug(), new Bug()));

		List<Bug> bugList = bugService.getBugList();
		assertEquals(3, bugList.size());
	}

	@Test
	public void testAddMethodIdReturnedNotNull() {
		Mockito.doAnswer(invocation -> {
			return invocation;
		}).when(bugRepository).add(Mockito.any());

		UUID randomUUID = UUID.randomUUID();

		UUID bugId = bugService.addBug(Bug.builder().id(randomUUID).build());

		assertEquals(randomUUID, bugId);
	}

	@Test
	public void testBugListFindById() {
		Mockito.when(bugRepository.getById(Mockito.any())).thenReturn(new Bug());

		Bug bug = bugService.findById(Mockito.any());

		assertNotNull(bug);
	}

	@Test
	public void testBugListFindByIdCompareUUID() {
		UUID randomUUID = UUID.randomUUID();
		Mockito.when(bugRepository.getById(Mockito.any())).thenReturn(Bug.builder().id(randomUUID).build());

		Bug bug = bugService.findById(randomUUID);

		assertEquals(randomUUID, bug.getId());
	}

	@Test
	public void testBugListFindByIdThrowElementNotFound() {
		Mockito.when(bugRepository.getById(Mockito.any())).thenThrow(new NoSuchElementException("err"));

		Bug bug = bugService.findById(UUID.randomUUID());

		assertNull(bug);
	}

	@Test
	public void testBugListDelete() {
		Mockito.doAnswer(invocation -> {
			return invocation;
		}).when(bugRepository).delete(Mockito.any());

		bugService.deleteBug(UUID.randomUUID());
	}

}
