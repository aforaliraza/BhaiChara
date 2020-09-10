package com.techpp.service;

import com.techpp.modal.ResponseObject;

public interface LocationsService {
	
	 public abstract ResponseObject findLocationBySuggestion(String address);

}
