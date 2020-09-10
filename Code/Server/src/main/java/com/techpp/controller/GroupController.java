package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.Group;
import com.techpp.modal.ResponseObject;
import com.techpp.service.GroupService;

@RestController
public class GroupController {

	@Autowired
	GroupService groupService;  
	
	
	@PostMapping(value = "/findAllGroups")
	public ResponseObject findAllGroups() {
		ResponseObject response = null;
		response = groupService.findAllGroups();
		return response;
	}
	
	@PostMapping(value = "/findByGroups")
	public ResponseObject findAllGroups(@RequestParam("groupcode") String groupCode) {
		ResponseObject response = null;
		response = groupService.findByGoupCode(groupCode);
		return response;
	}
	
	@PostMapping(value = "/addGroups")
	public ResponseObject addGroups(@ModelAttribute("group")Group group) {
		ResponseObject response = null;
		response = groupService.addGroups(group);
		return response;
	}
	
}
