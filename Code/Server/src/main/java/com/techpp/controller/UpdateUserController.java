package com.techpp.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;

@RestController
public class UpdateUserController {
	
	
	@PostMapping("/updateuserprofile")
	public ResponseObject editUser(@ModelAttribute User user) {
		
		
		ResponseObject rs = new ResponseObject();
		
		rs.setPayload("Ali");
		
		return rs;
		
		
	}

}
