package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techpp.dao.GroupDao;
import com.techpp.dao.rowmapper.GroupsRowMapper;
import com.techpp.modal.Group;
import com.techpp.utils.AppUtils;

@Repository("groupDao")
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Group> findAllGroups() {
		
		return jdbcTemplate.query(GROUP_CODE, new GroupsRowMapper()); 
	}
	
	@Override
	public int saveGroup(Group group) {
		int rows = 0;
		rows = jdbcTemplate.update(SAVE_NEW_GROUP_CODE, group.getName(), group.getAddress(), group.getGroupCode(), group.getLongitude(), group.getLatitude(), group.getMl());
		return rows ;
	}

	@Override
	public Group findByGoupCode(String groupCode) {
		List<Group> group = null;

		group = jdbcTemplate.query(FIND_BY_GROUP_CODE, new Object[] {groupCode}, new GroupsRowMapper());
		if(AppUtils.isNullOEmptyList(group)) {
			return null;
		} else 
			return group.get(0);
	}

}
