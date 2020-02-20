package logic.bean;

import logic.model.Address;

public class SignUpBean {
	private String usernameSign;
	private String passwordSign;
	private String nameSign;
	private String surnameSign;
	private String imageSign;
	private int isBarSign;
	private Address addressSign;
	
	public SignUpBean() {
		//this is empty
	}
	
	public String getUsernameSign() {
		return usernameSign;
	}
	
	public void setUsernameSign(String usernameSign) {
		this.usernameSign = usernameSign;
	}
	
	public String getPasswordSign() {
		return passwordSign;
	}
	
	public void setPasswordSign(String passwordSign) {
		this.passwordSign = passwordSign;
	}
	
	public String getNameSign() {
		return nameSign;
	}
	
	public void setNameSign(String nameSign) {
		this.nameSign = nameSign;
	}
	
	public String getSurnameSign() {
		return surnameSign;
	}
	
	public void setSurnameSign(String surnameSign) {
		this.surnameSign = surnameSign;
	}
	public String getImageSign() {
		return imageSign;
	}
	
	public void setImageSign(String imageSign) {
		this.imageSign = imageSign;
	}
	
	public int getIsBarSign() {
		return isBarSign;
	}
	
	public void setIsBarSign(int isBarSign) {
		this.isBarSign = isBarSign;
	}
	
	public Address getAddressSign() {
		return addressSign;
	}
	
	public void setAddressSign(Address addressSign) {
		this.addressSign = addressSign;
	}
}
