package logic.controller;

import java.util.List;

import logic.bean.NewCocktailBean;

public abstract class CocktailFilter {
	protected CocktailFilter next;
	protected String value;

	public CocktailFilter(String value) {
		this.value = value;
	}

	public final void setNext(CocktailFilter next) {
		this.next = next;
	}

	public abstract List<NewCocktailBean> filter(List<NewCocktailBean> list);
}
