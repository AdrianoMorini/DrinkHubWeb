package logic.model;

import java.util.List;
import java.util.ArrayList;


public class Recipe {

	private List<Ingredient> ingredients;
	private String procedure;
	
	public Recipe(String cocktailProcedure, List<Ingredient> b) {
		
		this.setProcedure(cocktailProcedure);
		this.ingredients = new ArrayList<>();
		this.setIngrList(b);
		
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	
	public void setIngrList(List<Ingredient> b) {
		this.ingredients = b;
	}

	public void addIngredient(Ingredient ingr) {
		this.ingredients.add(ingr);
	}
	
	public void removeIngredient(Ingredient ingr) {
		this.ingredients.remove(ingr);
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

}
