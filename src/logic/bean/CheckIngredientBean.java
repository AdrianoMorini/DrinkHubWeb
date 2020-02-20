package logic.bean;

public class CheckIngredientBean {
	
	private String ingredientName;
	private String ingredientQuantity;
	
	public CheckIngredientBean(String name, String quantity) {
		this.setIngredientName(name);
		this.setIngredientQuantity(quantity);
	}
	
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getIngredientQuantity() {
		return ingredientQuantity;
	}
	public void setIngredientQuantity(String ingredientQuantity) {
		this.ingredientQuantity = ingredientQuantity;
	}

}
