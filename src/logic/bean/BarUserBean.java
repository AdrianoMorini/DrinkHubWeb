package logic.bean;


public class BarUserBean {
  private String barUsername;
  private String barAddress;
    private Double latitudine;
    private Double longitudine;
    private String barName;
    private String barSurname;
    private String barImage;
  
  public String getBarUsername() {
    return barUsername;
  }
  
  public void setBarUsername(String username) {
	    this.barUsername = username;
	  }
  
  public String getBarAddress() {
    return barAddress;
  }

  public void setBarAddress(String barAddress) {
    this.barAddress = barAddress;
  }

  public Double getLatitudine() {
    return latitudine;
  }

  public void setLatitudine(Double latitudine) {
    this.latitudine = latitudine;
  }

  public Double getLongitudine() {
    return longitudine;
  }

  public void setLongitudine(Double longitudine) {
    this.longitudine = longitudine;
  }

	public String getBarImage() {
		return barImage;
	}

	public void setBarImage(String barImage) {
		this.barImage = barImage;
	}

	public String getBarSurname() {
		return barSurname;
	}

	public void setBarSurname(String barSurname) {
		this.barSurname = barSurname;
	}

	public String getBarName() {
		return barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}
}