package logic.model;

public class Address {
	
	protected Double latitude;
    protected Double longitude;
    protected String addr;
	
	public Address(Double latitude, Double longitude, String addr) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setAddress(addr);
    }
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
		
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
		
	}
	
	public void setAddress(String addr) {
		this.addr = addr;
	}
	
	public String getAddress() {
		return addr;
	}

}
