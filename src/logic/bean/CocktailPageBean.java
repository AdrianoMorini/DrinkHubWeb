package logic.bean;

import java.sql.Date;
import java.util.List;

import logic.model.Recipe;
import logic.model.Tag;

public class CocktailPageBean {
	private static String name;
	private static Recipe recipe;
	private static List<Tag> tags;
	private static String user; // id of user who posted this cocktail
	private static Date date;
	private static Integer id;
	private static String image;
	
	
	private CocktailPageBean() {
		// Do nothing 
	}
	
	public static String getName() {
		return name;
	}
	public  static void setName(String name) {
		CocktailPageBean.name = name;
	}
	public static Recipe getRecipe() {
		return recipe;
	}
	public static void setRecipe(Recipe recipe) {
		CocktailPageBean.recipe = recipe;
	}
	public static List<Tag> getTags() {
		return tags;
	}
	public static void setTags(List<Tag> tags) {
		CocktailPageBean.tags = tags;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		CocktailPageBean.user = user;
	}
	public static Date getDate() {
		return date;
	}
	public static void setDate(Date date) {
		CocktailPageBean.date = date;
	}
	public static Integer getId() {
		return id;
	}
	public static void setId(Integer id) {
		CocktailPageBean.id = id;
	}
	public static String getImage() {
		return image;
	}
	public static void setImage(String image) {
		CocktailPageBean.image = image;
	}

}
