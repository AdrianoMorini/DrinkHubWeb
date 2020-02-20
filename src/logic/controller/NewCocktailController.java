package logic.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.bean.CheckIngredientBean;
import logic.bean.CheckTagBean;
import logic.bean.NewCocktailBean;
import logic.dao.CocktailDao;
import logic.exception.IngredientListIsEmptyException;
import logic.exception.StringIsEmptyException;
import logic.model.Cocktail;
import logic.model.Ingredient;
import logic.model.Recipe;
import logic.model.Tag;

public class NewCocktailController {
	
    public void insertedIngredientIsValid(CheckIngredientBean a) throws StringIsEmptyException {
     	if(a.getIngredientName().equals("")) {
    		throw new StringIsEmptyException("Ingredient's name is required.");
		}
		else if(a.getIngredientQuantity().equals("")) {
			throw new StringIsEmptyException("Ingredient's quantity is required.");
		}
    	
    	Float.parseFloat(a.getIngredientQuantity());
    }
    
    
    public void insertedTagIsValid(CheckTagBean a) throws StringIsEmptyException {
    	if(a.getTag().equals("")) {
    		throw new StringIsEmptyException("Tag's name is required.");
    	}
    }
    
    public void insertedCocktailIsValid(NewCocktailBean b) throws IngredientListIsEmptyException, StringIsEmptyException {
    	
    	if(b.getName().equals("")) {
    		throw new StringIsEmptyException("ERROR: missing cocktail name");
    	}
    	
    	else if(b.getRecipe().getIngredients().isEmpty()) {
    		throw new IngredientListIsEmptyException("ERROR: missing ingredients");
    	}
    	
    	else if(b.getRecipe().getProcedure().equals("")) {
    		throw new StringIsEmptyException("ERROR: missing procedure");
    	}
    }
    
    
    public void newCocktailObject(NewCocktailBean b) {
    	LocalDate d = null;
		d = LocalDate.now();
		java.sql.Date date = java.sql.Date.valueOf(d);
		LoginController controller = LoginController.getInstance();
		
		ArrayList<Ingredient> ingrList = new ArrayList<>();
		for(int i = 0; i < b.getRecipe().getIngredients().size(); i++) {
			String n = b.getRecipe().getIngredients().get(i).getName();
			Float q = b.getRecipe().getIngredients().get(i).getQuantity();
			int t = b.getRecipe().getIngredients().get(i).getType();
			Ingredient ingr = new Ingredient(n, q, t);
			ingrList.add(ingr);
		}
		Recipe r = new Recipe(b.getRecipe().getProcedure(), ingrList);
		
		ArrayList<Tag> tagList = new ArrayList<>();
		for(int i = 0; i < b.getTags().size(); i++) {
			Tag t = new Tag(b.getTags().get(i).getTagName());
			tagList.add(t);
		}
		Cocktail c = new Cocktail(controller.getBean().getUsername(), b.getName(), tagList,
				  				  r, date, 0, b.getImage());
		
    	CocktailDao.setNewCocktail(c);
    }
}
