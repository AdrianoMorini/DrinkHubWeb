package logic.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewCocktailBean {
	
	private String name;
	private RecipeBean recipe;
	private ArrayList<TagBean> tags;
	private String image;
	private Date date;
	private String user;
	private int id;
	
	
	public NewCocktailBean() {
		
		this.name = "";
		this.tags = new ArrayList<>();
		this.recipe = new RecipeBean();
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TagBean> getTags() {
		return tags;
	}

	public void addTagBean(String tag) {
		TagBean t = new TagBean(tag);
		this.tags.add(t);
	}
	
	public void removeTagBean(int i) {
		this.tags.remove(i);
	}

	public RecipeBean getRecipe() {
		return recipe;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}