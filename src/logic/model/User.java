package logic.model;

public class User {
	protected String username;
    protected String name;
    protected String surname;
    protected String image;
    protected Address address;
   

    public User(String username, String name, String surname, 
    			String image, Address address) {
        this.setName(name);
        this.setSurname(surname);
        this.setUsername(username);
        this.setImage(image);
        this.setAddress(address);
    }

	public void setAddress(Address a) {
		this.address = a;
	}
	
	public Address getAddress() {
		return this.address;
	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
