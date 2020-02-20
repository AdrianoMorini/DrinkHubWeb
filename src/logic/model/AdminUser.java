package logic.model;

public class AdminUser extends User {

	private Boolean usA;
	
	public AdminUser(String username, String name, String surname,
			String image, Address address, Boolean usA) {
		super(username, name, surname, image, address);
		usA = true;
		
	}
	
	public Boolean getUsA() {
		return usA;
	}
	
	public void setUsB(Boolean usA) {
		this.usA = usA;
	}

}
