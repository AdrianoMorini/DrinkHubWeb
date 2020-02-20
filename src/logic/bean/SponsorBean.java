package logic.bean;

import java.sql.Date;

import logic.controller.LoginController;
import logic.controller.SponsorController;

public class SponsorBean {
	private double price;
	private String type;
	private String time;
	private static Date timeline;
	private static int typing;
	private static String userSponsor;
    private int typeSponsor;
    private Date timeSponsor;
	
	public SponsorBean() {
		this.price=0;
		this.type="";
		this.time=null;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static void calculate() {
		SponsorController controller = SponsorController.getInstance();
		controller.calculatePrice();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static Date getTimeline() 
	{
		return timeline;
	}

	public static void setTimeline(Date timeline) {
		SponsorBean.timeline = timeline;
	}
	
	public void saveSponsor(String price) {
		this.price=Double.valueOf(price);
	}
	
	public double saveSponsorPrice() {
		LoginController controller = LoginController.getInstance();
		return controller.getPrice();
	}

	public static int getTyping() {
		return typing;
	}

	public static void setTyping(int typing) {
		SponsorBean.typing = typing;
	}

	public String getUserSponsor() {
		return userSponsor;
	}

	public void setUserSponsor(String userSponsor) {
		SponsorBean.userSponsor = userSponsor;
	}

	public int getTypeSponsor() {
		return typeSponsor;
	}

	public void setTypeSponsor(int typeSponsor) {
		this.typeSponsor = typeSponsor;
	}

	public Date getTimeSponsor() {
		return timeSponsor;
	}

	public void setTimeSponsor(Date timeSponsor) {
		this.timeSponsor = timeSponsor;
	}
}
