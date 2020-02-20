package logic.model;

import java.util.ArrayList;
import java.util.List;


import java.sql.Date;


public class Cocktail {
	
	private String name;
	private Recipe recipe;
	private List<Tag> tags;
	private String user; // id of user who posted this cocktail
	private Date date;
	private Integer id;
	private String image;
	// add rate
	
	
	public Cocktail(String userID, String name, List<Tag> tagsList, Recipe r,
					Date cDate, int cocktailID, String cImage) {
		this.setCocktailUser(userID);
		this.setName(name);
		this.tags = new ArrayList<>();
		this.setTags(tagsList);
		this.setRecipe(r);
		this.setDate(cDate);
		this.setId(cocktailID);
		this.setImage(cImage);
	}
	
	
	public String getRecipeProcedure() {
		return this.recipe.getProcedure();
	}
	
	public List<Ingredient> getRecipeIngredients() {
		return this.recipe.getIngredients();
	}
	
	public void setRecipe(Recipe r) {
		this.recipe = r;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tagsList) {
		this.tags = tagsList;
	}
	
	public String getCocktailUser() {
		return user;
	}

	public void setCocktailUser(String userID) {
		this.user = userID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date cocktailDate) {
		this.date = cocktailDate;
	}
	
	
	public String getImage() {
		return this.image;
	}
	

	public void setImage(String image) {
		this.image = image;
	}
	

	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
}
