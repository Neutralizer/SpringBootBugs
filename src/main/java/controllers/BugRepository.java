package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import pojo.Bug;

//TODO add class documentation.
@Repository
public class BugRepository {

	private List<Bug> repository = new ArrayList<Bug>();

	{
		Bug bug = Bug.builder()
				.id(UUID.randomUUID())
				.title("Bug title")
				.description("bug description...")
				.build();

		repository.add(bug);
		repository.add(bug);
		repository.add(bug);
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
