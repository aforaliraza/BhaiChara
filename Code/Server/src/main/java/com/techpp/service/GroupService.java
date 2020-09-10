package com.techpp.service;

import com.techpp.modal.Group;
import com.techpp.modal.ResponseObject;

public interface GroupService {

	public abstract ResponseObject findAllGroups();
	public abstract ResponseObject addGroups(Group group);
	public abstract ResponseObject findByGoupCode(String groupCode); 
}
