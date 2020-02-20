package logic.controller;

import logic.bean.CocktailPostBean;
import logic.dao.CocktailPostDao;

public class CocktailPostController {
	private static CocktailPostController inst;
	private CocktailPostBean bean = new CocktailPostBean();

    public static CocktailPostController getInstance() {
        if (inst == null)
            inst = new CocktailPostController();
        return inst;
    }
    
    private CocktailPostController() {
    }
    
    public void findCocktailList() {
    	CocktailPostDao.findPostsByUsername();
    	
    }
    
    public void findSponsoredCocktail() {
    	CocktailPostDao.findSponsoredPosts();
    	
    }

	public CocktailPostBean getBean() {
		return bean;
	}

	public void setBean(CocktailPostBean bean) {
		this.bean = bean;
	}
}
