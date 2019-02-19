package com.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controllers.BugRepository;
import com.pojo.Bug;

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

	public Bug findById(UUID id) {
		Bug bug;

		try {
			bug = bugRepository.getById(id);
		} catch (NoSuchElementException e) {
			return null;
		}

		return bug;
	}

	public void deleteBug(UUID id) {
		bugRepository.delete(id);
	}

}
