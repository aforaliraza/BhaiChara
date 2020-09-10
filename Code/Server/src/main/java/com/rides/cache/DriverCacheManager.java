package com.rides.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.techpp.modal.Driver;

@Service
public class DriverCacheManager  {

	private static Map<String, Map<String, Driver>> allDrivers = new ConcurrentHashMap<String, Map<String, Driver>>();
	
	
	public Map<String, Driver> getDriversByGroupCode(String groupCode) {
		Map<String, Driver> driversByGroupCode = allDrivers.get(groupCode);		
		return driversByGroupCode;
	}
	
		
}
