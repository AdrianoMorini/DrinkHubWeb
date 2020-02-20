package logic.model;

public class BarUser extends User {
	
	private Boolean usBb; 
	
	public BarUser(String username, String name, String surname, 
					String barImg, Address address, Boolean usBb)  {
		super(username, name, surname, barImg, address);
		usBb = true;
	}

	public Boolean getUsBb() {
		return usBb;
	}

	public void setUsBb(Boolean usBb) {
		this.usBb = usBb;
	}
	
	

}
