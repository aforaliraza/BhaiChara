package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;
import com.techpp.service.RolesDefiningService;
import com.techpp.utils.RequestTypes;

@RestController
public class ChangeRolesController {
	
	@Autowired
	RolesDefiningService  rolesDefiningService;
	
	@PostMapping(value = "/changeroles")
	public ResponseObject creatAccount(@ModelAttribute("user") User user) {
		ResponseObject response = null;
		response = rolesDefiningService.changeRoles(user);
		response.setRequestType(RequestTypes.CHANGE_ROLES);
		return response;	
	}
	

}
