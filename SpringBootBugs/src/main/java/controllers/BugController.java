package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugController {
	
	@Autowired
	private BugService bugService;
	
	@GetMapping(value="/bug")
	public List<Bug> getAllBugs() {
		return bugService.getBugList();
	}
	
	@PostMapping(value="/bug")
	public String addBug(@RequestBody Bug bug) {
		return bugService.addBug(bug);
	}
	
	@GetMapping(value="/bug/{bugId}")
	public Bug getBugById(@PathVariable String bugId) {
		return bugService.findById(bugId);
	}
	
	@DeleteMapping("/bug/{bugId}")
	public void deleteBug(@PathVariable String bugId) {
			bugService.deleteBug(bugId);
	}

}
