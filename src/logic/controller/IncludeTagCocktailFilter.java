package logic.controller;

import java.util.Iterator;
import java.util.List;

import logic.bean.NewCocktailBean;
import logic.bean.TagBean;

public class IncludeTagCocktailFilter extends CocktailFilter {
		
	public IncludeTagCocktailFilter(String value) {
		super(value);
	}

	public List<NewCocktailBean> filter(List<NewCocktailBean> list) {
		Iterator<NewCocktailBean> iter = list.iterator();

		while (iter.hasNext()) {
			boolean keep = false;
			NewCocktailBean cb = iter.next(); // Get current element
			
			for (TagBean tb: cb.getTags()) {
				if (tb.getTagName().contains(this.value)) {
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
