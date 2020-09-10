package com.techpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpp.dao.UserDao;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;
import com.techpp.service.UserAuthenticationService;
import com.techpp.utils.AppUtils;
import com.techpp.utils.RespCodes;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

	@Autowired
	UserDao userDao;

	@Override
	public ResponseObject findUserByContact(String contactNumber) {
		User user = null;
		ResponseObject response = new ResponseObject();
		user = userDao.findUserByContact(contactNumber);
		if(AppUtils.isNull(user)) {
			response.setResponseCode("05");
			response.setResponseDesc("No Record Found");
		} else {
			response.setResponseCode("00");
			response.setResponseDesc("User Data found");
			response.setPayload(user);
		}
		return response;
	}

	@Override
	public ResponseObject saveUser(User user) {
		
		ResponseObject response = new ResponseObject();
         
         int rows;
         
         try {
       	  rows=userDao.saveUser(user);
          if(rows>0){
        	  response.setResponseCode(RespCodes.SUCCESS);
        	  response.setResponseDesc("Success");
          }else {
        	  response.setResponseCode(RespCodes.REQUEST_FAILED);
        	  response.setResponseDesc("Request Failed");
          }
          
         } catch (Exception e) {
        	 response.setResponseCode(RespCodes.REQUEST_FAILED);
       	  	 response.setResponseDesc("Request Failed");
 		}
       
		return response;
		
		}

	@Override
	public ResponseObject editUser(User user) {
		ResponseObject response = new ResponseObject();
		
		int rows;
        rows= userDao.editUser(user);
         if(rows>0){
      	  response.setResponseCode(RespCodes.SUCCESS);
      	  response.setResponseDesc("Success");
        }else {
      	  response.setResponseCode(RespCodes.REQUEST_FAILED);
      	  response.setResponseDesc("Request Failed");
        }
		return response;	}
}
