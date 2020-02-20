package logic.model;

public class User {
	protected String u;
    protected String n;
    protected String s;
    protected String i;
    protected Address a;
   

    public User(String u, String n, String s, 
    			String i, Address a) {
        this.setName(n);
        this.setSurname(s);
        this.setUsername(u);
        this.setImage(i);
        this.setAddress(a);
    }

	public void setAddress(Address a) {
		this.a = a;
	}
	
	public Address getAddress() {
		return this.a;
	}

    public String getUsername() {
		return u;
	}

	public void setUsername(String u) {
		this.u = u;
	}

	public String getName() {
		return n;
	}

	public void setName(String n) {
		this.n = n;
	}

	public String getSurname() {
		return s;
	}

	public void setSurname(String s) {
		this.s = s;
	}
	
	public String getImage() {
		return i;
	}

	public void setImage(String i) {
		this.i = i;
	}

}
