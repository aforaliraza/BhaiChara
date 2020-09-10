package com.techpp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.GroupDao;
import com.techpp.modal.Group;
import com.techpp.modal.ResponseObject;
import com.techpp.service.GroupService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RespCodes;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;
	
	@Override
	public ResponseObject findAllGroups() {
		
		List<Group> group = null;		
		ResponseObject response = new ResponseObject();
		
		group = groupDao.findAllGroups();

		if(AppUtils.isNull(group)) {
			response.setResponseCode(RespCodes.NO_DATA_FOUND);
			response.setResponseDesc("No Record Found");	
		} else {
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Driver Data found");
			response.setPayload(group);
		}		
		return response;
		
	}

	@Override
	public ResponseObject addGroups(Group group) {
		
		ResponseObject response = new ResponseObject();
        
        int rows;
        rows=groupDao.saveGroup(group);
         if(rows>0){
       	  response.setResponseCode("RespCodes.SUCCESS");
       	  response.setResponseDesc("Success");
         }else {
       	  response.setResponseCode("04");
       	  response.setResponseDesc("Request Failed");
         }
		return response;
	}

	@Override
	public ResponseObject findByGoupCode(String groupCode) {
		
		Group group = null;
		ResponseObject response = new ResponseObject();
		group = groupDao.findByGoupCode(groupCode);
		
		if(AppUtils.isNull(group)) {
			response.setResponseCode("RespCodes.NO_DATA_FOUND");
			response.setResponseDesc("No Record Found");
		} else {
			response.setResponseCode("RespCodes.SUCCESS");
			response.setResponseDesc("User Data found");
			response.setPayload(group);
		}
		return response;
	
	}

}
