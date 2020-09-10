package com.techpp.dao;

import com.techpp.modal.Location;

public interface LocationsDao {
	
	public final static String FIND_LOCATION_BY_SUGGESTION = "select * from locations where address like ? ";

	
	public abstract java.util.List<Location> findLocationBySuggestion(String address);

}
