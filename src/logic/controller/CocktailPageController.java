package logic.controller;

import logic.bean.IDBean;
import logic.dao.CocktailDao;

public class CocktailPageController {
	
	public void removeCocktailC(IDBean b) {
		CocktailDao.removeCocktailByID(b.getId());
	}

}