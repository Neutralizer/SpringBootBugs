package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BugService {

	private List<Bug> bugList = new ArrayList<>(
			Arrays.asList(new Bug("bug1", "bug1 desc"),new Bug("bug2", "bug2 desc"))
			);

	public List<Bug> getBugList() {
		return bugList;
	}

	public void setBugList(List<Bug> bugList) {
		this.bugList = bugList;
	}

	public String addBug(Bug bug) {
		bugList.add(new Bug(bug.getTitle(), bug.getDescription()));
		int index = bugList.indexOf(bug);
		return bugList.get(index).getId();
	}
	
	public Bug findById(String id) {
		for (Bug bug : bugList) {
			if(bug.getId().equals(id)) {
				return bug;
			}
		}
		
		return null;
	}
	
	public void deleteBug(String id) {
		for (Bug bug : bugList) {
			if(bug.getId().equals(id)) {
				bugList.remove(bug);
			}
		}
	}

}
