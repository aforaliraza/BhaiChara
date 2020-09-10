package com.techpp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.techpp.dao.VehicleDetailsDao;
import com.techpp.dao.rowmapper.VehicleDetailsRowMapper;
import com.techpp.modal.VehicleDetails;
import com.techpp.utils.AppUtils;

@Repository("VehicalDetailsDao")
public class VehicalDetailsDaoImpl implements VehicleDetailsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveVehicleDetails(VehicleDetails vehicleDetails) {
			int rows = 0;
			
			rows = jdbcTemplate.update(SAVE_VEHICLE_DETAILS, vehicleDetails.getMobileNumber(), vehicleDetails.getCarType(), vehicleDetails.getSeatAvailable(),
					vehicleDetails.getCarNumber(), vehicleDetails.getSource(), vehicleDetails.getDestination(), vehicleDetails.getDate(), vehicleDetails.getTime());
			
			return rows ;
		
	}
	@Override
	public VehicleDetails findVehicalByMobileNumber(String mobileNumber) {
		
		List<VehicleDetails> vehicleDetails = null;

		vehicleDetails = jdbcTemplate.query(FIND_VEHICLE_BY_MOBILE_NUM, new Object[] {mobileNumber}, new VehicleDetailsRowMapper());
		
		if(AppUtils.isNullOEmptyList(vehicleDetails)) {
		
			return null;
		}
		else 
	
			return vehicleDetails.get(0);
	}

		
	}
	
	


