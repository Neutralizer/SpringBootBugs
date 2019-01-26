package controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling the Bug operations.
 */
@RestController
public class BugController {

	@Autowired
	private BugService bugService;

	@GetMapping(value = "/bug")
	public List<Bug> getAllBugs() {
		return bugService.getBugList();
	}

	@PostMapping(value = "/bug")
	public UUID addBug(@RequestBody Bug bug) {
		return bugService.addBug(bug);
	}

	@GetMapping(value = "/bug/{bugId}")
	public Bug getBugById(@PathVariable UUID bugId) {
		Optional<Bug> bug = bugService.findById(bugId);

		if (!bug.isPresent()) {
			//return empty object if bug with this id is not found
			return new Bug();
		}

		return bug.get();
	}

	@DeleteMapping("/bug/{bugId}")
	public void deleteBug(@PathVariable UUID bugId) {
		bugService.deleteBug(bugId);
	}

}
