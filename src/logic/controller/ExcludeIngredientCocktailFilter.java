package logic.controller;

import java.util.Iterator;
import java.util.List;

import logic.bean.IngredientBean;
import logic.bean.NewCocktailBean;

public class ExcludeIngredientCocktailFilter extends CocktailFilter {
	
	public ExcludeIngredientCocktailFilter(String value) {
		super(value);
	}
	
	public List<NewCocktailBean> filter(List<NewCocktailBean> list) {
		Iterator<NewCocktailBean> iter = list.iterator();
		while (iter.hasNext()) {
			NewCocktailBean cb = iter.next(); // Get current element

			for (IngredientBean ib: cb.getRecipe().getIngredients()) {
				if (ib.getName().contains(this.value)) {
					iter.remove();
					break;
				}
			}
		}

		if (this.next != null) {
			return this.next.filter(list);
		} else {
			return list;
		}
	}

}
