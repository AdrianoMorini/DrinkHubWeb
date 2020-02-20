package logic.controller;

import java.util.Iterator;
import java.util.List;

import logic.bean.NewCocktailBean;
import logic.bean.TagBean;

public class ExcludeTagCocktailFilter extends CocktailFilter {

	public ExcludeTagCocktailFilter(String value) {
		super(value);
	}

	public List<NewCocktailBean> filter(List<NewCocktailBean> list) {
		Iterator<NewCocktailBean> iter = list.iterator();

		while (iter.hasNext()) {
			NewCocktailBean cb = iter.next(); // Get current element
			
			for (TagBean tb: cb.getTags()) {
				if (tb.getTagName().contains(this.value)) {
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
