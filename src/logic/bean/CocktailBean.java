package logic.bean;

import java.sql.Date;
import java.util.List;

import logic.model.Cocktail;
import logic.model.Recipe;
import logic.model.Tag;

public class CocktailBean {
	private String name;
	private Recipe recipe;
	private List<Tag> tags;
	private String user; // id of user who posted this cocktail
	private Date date;
	private Integer id;
	private List<Cocktail> cocktails;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Cocktail> getCocktails() {
		return cocktails;
	}
	public void setCocktails(List<Cocktail> cocktails) {
		this.cocktails = cocktails;
	}

}
