package com.techpp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;
import com.techpp.service.UserAuthenticationService;

@RestController
public class UserAuthenticationController {

	@Autowired
	UserAuthenticationService userAuthenticationService;  
	
	@PostMapping(value = "/checkuser")
	public ResponseObject getUserInfoByMobile(@RequestParam("mobilenum") String mobileNum) {
		ResponseObject response = null;
		response = userAuthenticationService.findUserByContact(mobileNum);
		return response;
	}
	
	
	@PostMapping(value = "/createaccount")
	public ResponseObject creatAccount(@ModelAttribute("user") User user) {
		ResponseObject response = null;
		response = userAuthenticationService.saveUser(user);	
		return response;	
	}
	
	@PostMapping(value = "/profilesetting")
	public ResponseObject profileSetting(@ModelAttribute("user") User user) {
		ResponseObject response = null;
		response = userAuthenticationService.editUser(user);	
		return response;	
	}

}
