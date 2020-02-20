package logic.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CocktailBean;
import logic.bean.CocktailPageBean;
import logic.controller.FindCocktailController;
import logic.model.Ingredient;

/**
 * Servlet implementation class CocktailPostServlet
 */
@WebServlet("/CocktailPostServlet")
public class CocktailPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CocktailPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("seePost", request.getParameter("id"));
		FindCocktailController cocktailController = new FindCocktailController();
		CocktailBean cb = new CocktailBean();
		cb.setId(Integer.parseInt(request.getParameter("id")));
		
		cocktailController.fillUpCocktailPage(cb);
		request.getSession().setAttribute("name",CocktailPageBean.getName());
		request.getSession().setAttribute("proc",CocktailPageBean.getRecipe().getProcedure());
		List<Ingredient> cIngrList = CocktailPageBean.getRecipe().getIngredients();
		String cIngr = "";
		 int lenIngr = cIngrList.size();
		 for(int i = 0; i < lenIngr; i++) {
			 String ingrName = cIngrList.get(i).getName();
			 String ingrQuant = cIngrList.get(i).getQuantity().toString();
			 int t = cIngrList.get(i).getType();
			 String ingr = "";
			 if(t == 1) {
				 ingr = ingrName + " (" + ingrQuant + " g)\n";
			 }
			 else if(t == 2) {
				 ingr = ingrName + " (" + ingrQuant + " ml)\n";
			 }
			 else {
				 ingr = ingrName + " (" + ingrQuant + ")\n";
			 }
			 cIngr = cIngr.concat(ingr);
		 }
		 request.getSession().setAttribute("ingr",cIngr);
		
		String nextJSP = "/cocktailPost.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
