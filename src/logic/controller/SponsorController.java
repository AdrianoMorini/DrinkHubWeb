package logic.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.SponsorBean;
import logic.dao.SponsorDao;
import logic.model.Sponsor;
import logic.model.User;

/* singleton class
 * */

public class SponsorController {
	private static SponsorController i;
	public User u;
	public Sponsor s;
	public SponsorBean bean = new SponsorBean();
    
    private double price = 0;
	
    //synchronized
	public static synchronized SponsorController getInstance() {
        if (i == null)
            i = new SponsorController();
        return i;
    }
	
	private SponsorController() {
    }
	
	public void calculatePrice() {
    	double pr = 5;
    	Date future = null;
    	int type = 0;
    	LocalDate actual = null;
    	
    	String tp = bean.getType();
    	String tm = bean.getTime();
    	
    	int k = 0;
    	if( tm.equals("1 month") ) {
    		pr = pr * 30;
    		k = 1;
    	}
    	else if(tm.equals("2 months (-10%)")) {
    		k = 2;
    		pr = 4.5 * 60;
    	}
    	else if(tm.equals("3 months (-15%)")) {
    		k = 3;
    		pr = 4.25 * 90;
    	}
    	else if(tm.equals("6 months (-20%)")) {
    		k = 6;
    		pr = 4 * 180;
    	}
    	else if(tm.equals("1 year (-30%)")) {
    		k = 12;
    		pr = 3.5 * 365;
    	}
    	else {
    		k = 0;
    		pr = 0;
    	}
    	
    	if(k != 0) {
    		actual = LocalDate.now().plusMonths(k);
    	}
    	else {
    		actual = null;
    	}
    	
    	future = java.sql.Date.valueOf(actual);
    	SponsorBean.setTimeline(future);
    	
    	if( tp.equals("Profile")) {
    		pr = pr*1.5;
    		type = 1;
    	}
    	else if( tp.equals("Post")) {
    		//do nothing
    		type = 2;
    	}
    	else if( tp.equals("Both")) {
    		pr = pr*1.8;
    		type = 3;
    	}
    	else {
    		pr = 0;
    		type = 0;
    	}
    	
    	SponsorBean.setTyping(type);
    	this.bean.setPrice(pr);
    }
    
    public boolean paymentProbab() {
    	double random = Math.random() * 100;
    	Logger logger = Logger.getLogger( SponsorController.class.getName()); 
		logger.log(Level.SEVERE, "Something went wrong: {0} ", random);
        return(random < 80);
    }
    
    public void searchExistingSponsor() {
    	SponsorDao.findSponsorByUsername(this.bean);	

    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public SponsorBean getBean() {
		return this.bean;
	}

	public void setBean(SponsorBean bean) {
		this.bean = bean;
	}
	
	public boolean saveSponsor() {
		return SponsorDao.setNewSponsor();
	}
	
	public void cleanSponsorDB() {
		SponsorDao.deleteSponsor();
	}
}
