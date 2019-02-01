package test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import controllers.BugRepository;
import pojo.Bug;

public class TestBugRepository {
	BugRepository b;

	@Before
	public void setUp() throws Exception {
		b = new BugRepository();
	}

	@Test
	public void testGetBugNotNull() {
		List<Bug> all = b.getAll();
		assertNotNull(all);
	}

	@Test
	public void testGetBugAmount() {
		List<Bug> all = b.getAll();
		assertEquals(3, all.size());
	}

	@Test
	public void testGetById() {
		List<Bug> all = b.getAll();
		UUID uuid = all.get(0).getId();

		Bug bug = b.getById(uuid);
		assertEquals(uuid, bug.getId());
	}

	@Test
	public void testGetByIdGetTitle() {
		List<Bug> all = b.getAll();
		UUID uuid = all.get(0).getId();
		String title = all.get(0).getTitle();

		Bug bug = b.getById(uuid);
		assertEquals(title, bug.getTitle());
	}

	@Test
	public void testGetByIdGetDesc() {
		List<Bug> all = b.getAll();
		UUID uuid = all.get(0).getId();
		String desc = all.get(0).getDescription();

		Bug bug = b.getById(uuid);
		assertEquals(desc, bug.getDescription());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetBugNoSuchElementExeption() {
		b.getById(UUID.randomUUID());
	}

	@Test
	public void testAddBugSizeCheck() {
		Bug bug = new Bug(UUID.randomUUID(), "new bug", "new desc", new ArrayList<>());
		b.add(bug);
		List<Bug> all = b.getAll();
		assertEquals(4, all.size());
	}

	@Test
	public void testAddBugEqualsCheck() {
		Bug bug = new Bug(UUID.randomUUID(), "new bug", "new desc", new ArrayList<>());
		b.add(bug);
		assertEquals(bug, b.getAll().get(3));
	}

	@Test
	public void testAddBugNotEqualsCheck() {
		Bug bug = new Bug(UUID.randomUUID(), "new bug", "new desc", new ArrayList<>());
		b.add(bug);
		assertNotEquals(bug, b.getAll().get(0));
	}

	@Test
	public void testDeleteBugCheckListLength() {
		List<Bug> all = b.getAll();
		b.delete(all.get(0).getId());
		List<Bug> allAfterDelete = b.getAll();
		assertEquals(2, allAfterDelete.size());
	}

	@Test
	public void testDeleteBugDeleteEverything() {
		List<Bug> all = b.getAll();
		b.delete(all.get(0).getId());
		b.delete(all.get(0).getId());
		b.delete(all.get(0).getId());

		List<Bug> allAfterDelete = b.getAll();
		assertEquals(0, allAfterDelete.size());
	}

}
