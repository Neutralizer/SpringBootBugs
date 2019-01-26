package controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugService {

	@Autowired
	private BugRepository bugRepository;

	public List<Bug> getBugList() {
		return bugRepository.getAll();
	}

	public UUID addBug(Bug bug) {
		bugRepository.add(bug);
		return bug.getId();
	}

	public Optional<Bug> findById(UUID id) {
		Bug bug;

		try {
			bug = bugRepository.getById(id);
		} catch (NoSuchElementException e) {
			return Optional.empty();
		}

		return Optional.ofNullable(bug);
	}

	public void deleteBug(UUID id) {
		bugRepository.delete(id);
	}

}
