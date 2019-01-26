package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import controllers.Bug;
import controllers.BugRepository;

public class TestBugRepository {
	BugRepository b;

	@Before
	public void setUp() throws Exception {
		b=new BugRepository();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBugNotNull() {
		List<Bug> all = b.getAll();
		System.out.println(all.get(0));
		assertNotNull(all);
	}

	@Test
	public void testGetBugAmount() {
		List<Bug> all = b.getAll();
		assertEquals(3, all.size());
	}

}
