package logic.controller;

import logic.bean.CocktailFilterBean;
import logic.exception.StringIsEmptyException;

public class CocktailFilterFactory {
    
    public CocktailFilter makeFilter(CocktailFilterBean cfb) throws StringIsEmptyException {
    	CocktailFilter f;
    	
    	if (cfb.getValue().equals("")) {
    		throw new StringIsEmptyException("Filter value is empty");
    	}
    	
        switch (cfb.getFilterType()) {
            case INCLUDE_INGREDIENT:
                f = new IncludeIngredientCocktailFilter(cfb.getValue());
                break;
            case EXCLUDE_INGREDIENT:
            	f = new ExcludeIngredientCocktailFilter(cfb.getValue());
                break;
            case INCLUDE_TAG:
            	f = new IncludeTagCocktailFilter(cfb.getValue());
                break;
            case EXCLUDE_TAG:
            	f = new ExcludeTagCocktailFilter(cfb.getValue());
                break;
            default:
            	f = null;
        }
        
        return f;
        
    }

}
