package modal;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String mobileNumber;
    private String licenceNO;
    private String status;
    private String curentLocationLong;
    private String curentLocationLat;
    private String groupCode;

    VehicleDetails vehicle = new VehicleDetails();
    User user  = new User();
    Ride ride  = new Ride();
    List<Rider> riders =  new ArrayList<>();
    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public VehicleDetails getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDetails vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getLicenceNO() {
        return licenceNO;
    }
    public void setLicenceNO(String licenceNO) {
        this.licenceNO = licenceNO;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCurentLocationLong() {
        return curentLocationLong;
    }
    public void setCurentLocationLong(String curentLocationLong) {
        this.curentLocationLong = curentLocationLong;
    }
    public String getCurentLocationLat() {
        return curentLocationLat;
    }
    public void setCurentLocationLat(String curentLocationLat) {
        this.curentLocationLat = curentLocationLat;
    }
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", licenceNO='" + licenceNO + '\'' +
                ", status='" + status + '\'' +
                ", curentLocationLong='" + curentLocationLong + '\'' +
                ", curentLocationLat='" + curentLocationLat + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", vehicle=" + vehicle +
                ", user=" + user +
                ", ride=" + ride +
                '}';
    }
}
