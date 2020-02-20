package logic.bean;

import logic.controller.LoginController;

public class LatitudeLongitudeBean {

	public LatitudeLongitudeBean() {
		//empty method
	}
	
	public Double[] getLatiLong() {
		Double[] s;
		LoginController controller = LoginController.getInstance();
		s = controller.getLoggedUserLatitudeLongitude();
		return s;
	}
	
}
