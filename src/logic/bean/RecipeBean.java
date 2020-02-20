package logic.bean;

import java.util.ArrayList;
import java.util.List;

public class RecipeBean {
	
	private String procedure;
	private ArrayList<IngredientBean> ingredients;
	
	public RecipeBean() {
		this.procedure = "";
		this.ingredients = new ArrayList<>();
	}
	
	public String getProcedure() {
		return procedure;
	}
	
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public List<IngredientBean> getIngredients() {
		return ingredients;
	}

	public void addIngredientBean(String name, Float quantity, Integer type) {
		IngredientBean i = new IngredientBean();
		i.setName(name);
		i.setQuantity(quantity);
		i.setType(type);
		this.ingredients.add(i);
	}
	
	public void removeIngredientBean(IngredientBean i) {
		this.ingredients.remove(i);
	}

}
