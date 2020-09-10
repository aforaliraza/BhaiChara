package com.techpp.dao;

import java.util.List;

import com.techpp.modal.Group;


public interface GroupDao {
	public final static String GROUP_CODE = "SELECT name, address, group_code, longitude, latitude, ml  FROM organizations";
	public final static String SAVE_NEW_GROUP_CODE = "insert into organizations (name, address, group_code, longitude, latitude, ml ) VALUES (?,?,?,?,?,?)";
	public final static String FIND_BY_GROUP_CODE = "SELECT name, address, group_code, longitude, latitude, ml FROM organizations where group_code = ?";
	
	public abstract List<Group> findAllGroups();
	public abstract Group findByGoupCode(String groupCode);
	public abstract int saveGroup(Group group);

}
