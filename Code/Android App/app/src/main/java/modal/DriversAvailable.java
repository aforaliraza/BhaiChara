package modal;

import java.util.List;

public class DriversAvailable {
	
	List<Driver> payload ;

	public List<Driver> getPayload() {
		return payload;
	}

	public void setPayload(List<Driver> payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Driversonline [payload=" + payload + "]";
	}
	

	
}
