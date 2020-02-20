package logic.controller;

import java.util.List;

import logic.bean.CocktailBean;
import logic.bean.CocktailPageBean;
import logic.bean.IngredientBean;
import logic.bean.TagBean;
import logic.dao.CocktailDao;
import logic.exception.StringIsEmptyException;
import logic.model.Cocktail;
import logic.model.Recipe;

public class FindCocktailController {
	
	public FindCocktailController() {
		// Do nothing.
	}
	
	 public void isInsertedCocktailNameValid(CocktailBean a) throws StringIsEmptyException {
	    	if (a.getName().equals("")) {
	    		throw new StringIsEmptyException("Inserted string is empty");
			} 
	 }
	
	public List<Cocktail> findCocktailWithName(CocktailBean cocktailNameBean) {
    	String cocktailName = cocktailNameBean.getName();
    	return CocktailDao.findCocktailByName(cocktailName);
		
	}

	public  List<Cocktail> findCocktailWithTag(TagBean cocktailTagBean) {
		String cocktailTag = cocktailTagBean.getTagName();
		return CocktailDao.findCocktailByTag(cocktailTag);
	}

	public List<Cocktail> findCocktailWithIngredient(IngredientBean cocktailIngredientBean) {
		String cocktailIngredient = cocktailIngredientBean.getName();
		return CocktailDao.findCocktailByIngredient(cocktailIngredient);
	}

	public void fillUpCocktailPage(CocktailBean cocktailBean) {
		
		Cocktail c = CocktailDao.findCocktailByID(cocktailBean.getId());
		CocktailPageBean.setName(c.getName());
		CocktailPageBean.setTags(c.getTags());
		CocktailPageBean.setUser(c.getCocktailUser());
		CocktailPageBean.setDate(c.getDate());
		CocktailPageBean.setId(c.getId());
		Recipe r = new Recipe(c.getRecipeProcedure(), c.getRecipeIngredients());
		CocktailPageBean.setRecipe(r);
		CocktailPageBean.setImage(c.getImage());

		
	}

}
