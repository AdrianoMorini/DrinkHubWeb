package logic.controller;

import java.util.Iterator;
import java.util.List;

import logic.bean.IngredientBean;
import logic.bean.NewCocktailBean;

public class IncludeIngredientCocktailFilter extends CocktailFilter {
	
	public IncludeIngredientCocktailFilter(String value) {
		super(value);
	}

	public List<NewCocktailBean> filter(List<NewCocktailBean> list) {
		Iterator<NewCocktailBean> iter = list.iterator();

		while (iter.hasNext()) {
			boolean keep = false;
			NewCocktailBean cb = iter.next(); // Get current element
			
			
			for (IngredientBean ib: cb.getRecipe().getIngredients()) {
				if (ib.getName().contains(this.value)) {
					keep = true;
					break;
				}
			}
			
			if (!keep)
				iter.remove();
		}

		if (this.next != null) {
			return this.next.filter(list);
		} else {
			return list;
		}
	}
}
