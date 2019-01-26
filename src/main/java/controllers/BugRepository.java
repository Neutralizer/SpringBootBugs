package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

//TODO add class documentation.
@Repository
public class BugRepository {

	private List<Bug> repository = new ArrayList<Bug>();

	@PostConstruct
	private void init() {
		Bug bug = Bug.builder()
				.id(UUID.randomUUID())
				.title("Bug title")
				.description("bug description...")
				.build();

		List<Bug> bugs = new ArrayList<Bug>();
		bugs.add(bug);
		bugs.add(bug);
		bugs.add(bug);
	}

	public List<Bug> getAll() {
		return repository;
	}

	public Bug getById(UUID bugId) {
		for (Bug bug : repository) {
			if (bug.getId().equals(bugId)) {
				return bug;
			}
		}

		throw new NoSuchElementException("Bug " + bugId + " cannot be found");
	}

	public void add(Bug bug) {
		repository.add(bug);
	}

	public void delete(UUID bugId) {
		Bug bug = getById(bugId);
		repository.remove(bug);
	}

}
