package com.techpp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.UserDao;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;
import com.techpp.service.RolesDefiningService;
import com.techpp.utils.RequestTypes;
import com.techpp.utils.RespCodes;


@Service
public class RolesDefiningServiceImpl  implements  RolesDefiningService {


	@Autowired
	UserDao userDao;

	@Override
	public ResponseObject changeRoles(User user) {

		ResponseObject response = new ResponseObject();
		response.setResponseType(RequestTypes.CHANGE_ROLES);
		int rows;

		rows = userDao.changeRoles(user);

		if(rows > 0){
			response.setResponseCode(RespCodes.SUCCESS);
			response.setResponseDesc("Success");
		}else {
			response.setResponseCode(RespCodes.REQUEST_FAILED);
			response.setResponseDesc("Request Failed");
		}
		return response;
	}



}
