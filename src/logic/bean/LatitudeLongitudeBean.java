package logic.bean;

import logic.controller.LoginController;

public class LatitudeLongitudeBean {
	
	private Double[] s;
	
	
	public LatitudeLongitudeBean() {

	}
	
	public Double[] getLatiLong() {
		LoginController controller = LoginController.getInstance();
		s = controller.getLoggedUserLatitudeLongitude();
		return s;
	}
	
}
