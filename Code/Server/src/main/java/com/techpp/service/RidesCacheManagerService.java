
package com.techpp.service;

import java.util.List;
import java.util.Map;

import com.techpp.modal.Driver;
import com.techpp.modal.Group;
import com.techpp.modal.ResponseObject;

public interface RidesCacheManagerService {

	public abstract void initCache();
	public abstract void addDriver(Group group, Driver driver);
	public abstract void removeDriver(Group group, Driver driver);
	public abstract void updateDriverLocation(Group group, Driver driver);
	public abstract void updateVehicalStatus(Group group, Driver driver);
	public ResponseObject getDriversByGroupCode(String groupCode);
	public 	Map<String, Driver> getDriversMapByGroupCode(String groupCode);
	public ResponseObject getDriver(String groupCode, String mobileNumber);
	
}
