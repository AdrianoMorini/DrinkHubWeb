package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.ProfileBean;
import logic.bean.RadiusBean;
import logic.bean.BarUserBean;
import logic.bean.FindUserBean;
import logic.dao.BarUserDao;
import logic.dao.BarUserDaoFactory;
import logic.exception.StringIsEmptyException;
import logic.model.BarUser;
import logic.model.User;

public class FindBarUserController {

	private String dbType = "mariaDB";
	private static FindBarUserController instance = null;
	
	
	private FindBarUserController() {

	}
	
	public synchronized static final FindBarUserController getInstance() {
		if (FindBarUserController.instance == null) {
			FindBarUserController.instance = new FindBarUserController();
		}
		return instance;
	}
	
	public boolean findBar(List<BarUser> Bar ) {
		BarUserDao dao = BarUserDaoFactory.getInstance().createUtenteBarDao(dbType);
		ArrayList<BarUser> B = dao.getAllBarsByLocation();
    	
    	if(B != null) {
    		//fare new utente
    	}
    	return (B != null);
    }
    
    public List<BarUserBean> getBars() {
    	
    	BarUserDao dao = BarUserDaoFactory.getInstance().createUtenteBarDao(dbType);
    	ArrayList<BarUserBean> utentiBB = new ArrayList<>();
    	BarUserBean current;
    	for (BarUser utenteB : dao.getAllBarsByLocation()) {
			current = new BarUserBean();
			current.setBarAddress(utenteB.getAddress().getAddress());
			current.setBarUsername(utenteB.getUsername());
			current.setLatitudine(utenteB.getAddress().getLatitude());
			current.setLongitudine(utenteB.getAddress().getLongitude());
			current.setBarName(utenteB.getName());
			current.setBarSurname(utenteB.getSurname());
			current.setBarImage(utenteB.getImage());
			utentiBB.add(current);
		}
    	return utentiBB;
    	
    }
    
    private List<BarUser> getOriginalBars(){
    	BarUserDao dao = BarUserDaoFactory.getInstance().createUtenteBarDao(dbType);
    	return dao.getAllBarsByLocation();
    }
    
    public boolean isInsertedBarUsernameValid(BarUserBean a) throws StringIsEmptyException {
    	if (a.getBarUsername().equals("")) {
    		throw new StringIsEmptyException("Inserted string is empty");	
		} 
    	for (BarUser b : this.getOriginalBars()) {
			if (a.getBarUsername().equals(b.getUsername())) {
				return true;
			}
		}
    	return false;
    }
    
    public boolean isInsertedUserUsernameValid(FindUserBean u) throws StringIsEmptyException{
    	if (u.getUsername().equals("")) {
			throw new StringIsEmptyException("Inserted string is empty");
		}
    	for (User s : this.getOriginalBarsByName()) {
			if (u.getUsername().equals(s.getUsername())) {
				return true;
			}
		}
    	return false;
    }
    
    private List<User> getOriginalBarsByName() {
    	BarUserDao dao = BarUserDaoFactory.getInstance().createUtenteBarDao(dbType);
    	return dao.getAllUserByName();
	}

	public void fillUpProfile(BarUserBean c) {
    	for (BarUser b : this.getOriginalBars()) {
			if (c.getBarUsername().equals(b.getUsername())) {
				ProfileBean.setUsername(b.getUsername());
				ProfileBean.setUName(b.getName());
				ProfileBean.setSurname(b.getSurname());
				ProfileBean.setImage(b.getImage());
			}
		}
    }
	
	public void fillUpProfileByName(FindUserBean q) {
		for (User e : this.getOriginalBarsByName()) {
			if (q.getUsername().equals(e.getUsername())) {
				ProfileBean.setUsername(e.getUsername());
				ProfileBean.setUName(e.getName());
				ProfileBean.setSurname(e.getSurname());
				ProfileBean.setImage(e.getImage());
			}
		}
	}
    
    public Double[] getLatiLong() {
    	return LoginController.getInstance().getLoggedUserLatitudeLongitude();
	}
    
    public void insertedRadiusIsValid(RadiusBean r)  {
    	Integer.parseInt(r.getRadius());
    }
    
}
