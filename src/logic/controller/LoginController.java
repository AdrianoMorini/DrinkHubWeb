package logic.controller;

import logic.bean.LoginBean;
import logic.dao.UserDao;
import logic.model.User;

public class LoginController {
    private static LoginController inst;
    public User u;
    private double price = 0;
    public LoginBean bean = new LoginBean();
    

    public static LoginController getInstance() {
        if (inst == null)
            inst = new LoginController();
        return inst;
    }

    private LoginController() {
    }
    
    public boolean findIdentity() throws ClassNotFoundException {
    	this.bean = UserDao.findByUserAndPassword(this.bean);
    	return (this.bean.getName() != null);
    }
    
    public Double[] getLoggedUserLatitudeLongitude() {
    	Double[] d = new Double[] {0.0, 0.0};
    	Double lat = this.bean.getAddr().getLatitude();
    	Double lon = this.bean.getAddr().getLongitude();
    	d[0] = lat;
    	d[1] = lon;
    	return d;
    }
    
    public boolean paymentProbab() {
    	double random = Math.random() * 100;
        return(random > 80);
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public LoginBean getBean() {
		return this.bean;
	}

	public void setBean(LoginBean bean) {
		this.bean = bean;
	}
}