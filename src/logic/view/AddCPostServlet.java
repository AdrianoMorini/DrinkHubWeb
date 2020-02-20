package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.NewCocktailBean;
import logic.controller.NewCocktailController;
import logic.exception.IngredientListIsEmptyException;
import logic.exception.StringIsEmptyException;

/**
 * Servlet implementation class AddCPostServlet
 */
@WebServlet("/AddCPostServlet")
public class AddCPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewCocktailBean newCBean = new NewCocktailBean();
	private NewCocktailController contr = new NewCocktailController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		newCBean = (NewCocktailBean)request.getSession().getAttribute("bean");
		contr = (NewCocktailController)request.getSession().getAttribute("controller");
		String cocktailName = request.getParameter("cocktailName");
		String cocktailProcedure = request.getParameter("procedure");
		newCBean.setName(cocktailName);
    	newCBean.getRecipe().setProcedure(cocktailProcedure);
		newCBean.setImage("image/tick.png");
		System.out.println("sono qui\n" + cocktailName + ", " + cocktailProcedure);
		System.out.println("tag-> " + newCBean.getTags().get(0));
    	
    	try {
    		contr.insertedCocktailIsValid(newCBean);
    	} catch (StringIsEmptyException | IngredientListIsEmptyException e) {
    		request.getSession().setAttribute("newPostMessage", e.getMessage());
    		response.sendRedirect(request.getHeader("Referer"));
    		return;
    	} 
		
    	String nextJSP = "/Homepage.jsp";
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

	public NewCocktailBean getNewCBean() {
		return newCBean;
	}

	public void setNewCBean(NewCocktailBean newCBean) {
		this.newCBean = newCBean;
	}

	public NewCocktailController getContr() {
		return contr;
	}

	public void setContr(NewCocktailController contr) {
		this.contr = contr;
	}

}
