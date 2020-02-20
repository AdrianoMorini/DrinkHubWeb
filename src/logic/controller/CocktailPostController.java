package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.CocktailFilterBean;
import logic.bean.CocktailPostBean;
import logic.bean.NewCocktailBean;
import logic.dao.CocktailPostDao;
import logic.exception.StringIsEmptyException;
import logic.model.Cocktail;


public class CocktailPostController {
	private static CocktailPostController inst;
	private CocktailPostBean bean = new CocktailPostBean();
	private List<NewCocktailBean> cocktailBeans = new ArrayList<>();
	private CocktailFilter filter;

    public static CocktailPostController getInstance() {
        if (inst == null)
            inst = new CocktailPostController();
        return inst;
    }
    
    private CocktailPostController() {
    }
    
    public void findCocktailList() {

    	this.cocktailBeans.removeIf(c -> true); // Remove all
    	CocktailPostDao.findPostsByUsername();
    	int len = this.bean.getPostList().size();
    	for(int j = 0; j < len; j++) {
        	NewCocktailBean nCBean = new NewCocktailBean();
        	
    		Cocktail b = this.bean.getPostList().get(j);
    		for(int i = 0; i < b.getRecipeIngredients().size(); i++) {
    			String n = b.getRecipeIngredients().get(i).getName();
    			Float q = b.getRecipeIngredients().get(i).getQuantity();
    			int t = b.getRecipeIngredients().get(i).getType();
    			nCBean.getRecipe().addIngredientBean(n, q, t);
    		}

    		nCBean.getRecipe().setProcedure(b.getRecipeProcedure());
    		
    		for(int i = 0; i < b.getTags().size(); i++) {
            	nCBean.addTagBean(b.getTags().get(i).getTag());
    		}
    		
        	nCBean.setName(b.getName());
        	nCBean.setImage(b.getImage());
        	nCBean.setDate(b.getDate());
        	nCBean.setUser(b.getCocktailUser());
        	nCBean.setId(b.getId());
        	
        	this.cocktailBeans.add(nCBean);
    	
    		}
    
    }
    
    public void findSponsoredCocktail() {
    	CocktailPostDao.findSponsoredPosts();
    }
    
    public void addFilter(CocktailFilterBean fb) throws StringIsEmptyException {
    	CocktailFilter f = (new CocktailFilterFactory()).makeFilter(fb);
    	
    	if (f != null) {
    		f.setNext(this.filter);
    	}
    	
    	this.filter = f;
    }
    
    public void removeAllFilters() {
    	this.filter = null;
    }

	public CocktailPostBean getBean() {
		return bean;
	}

	public void setBean(CocktailPostBean bean) {
		this.bean = bean;
	}
	
	public List<NewCocktailBean> getCocktailBeans() {
		
		if (this.filter != null)
			return this.filter.filter(new ArrayList<NewCocktailBean>(this.cocktailBeans));
		else
			return this.cocktailBeans;
	}
}
