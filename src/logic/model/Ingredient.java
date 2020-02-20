package logic.model;

public class Ingredient {
	
	private String name;
	private Float quantity;
	// type 0: float, 1:grammi, 2:millilitri
	private Integer type;
	
	public Ingredient(String n, Float q, int t) {
		this.setName(n);
		this.setQuantity(q);
		this.setType(t);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String n = name.substring(0,1).toUpperCase() + name.substring(1,name.length()).toLowerCase();
		this.name = n;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
