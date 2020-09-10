package com.techpp.service;

import com.techpp.modal.ResponseObject;
import com.techpp.modal.User;

public interface UserAuthenticationService {
	
	public abstract ResponseObject findUserByContact(String contactNumber);
	public abstract ResponseObject saveUser(User user);
	public abstract ResponseObject editUser(User user);
	
}
