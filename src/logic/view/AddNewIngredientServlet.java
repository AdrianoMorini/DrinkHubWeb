package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CheckIngredientBean;
import logic.bean.CheckTagBean;
import logic.bean.NewCocktailBean;
import logic.controller.NewCocktailController;
import logic.exception.IngredientListIsEmptyException;
import logic.exception.StringIsEmptyException;

/**
 * Servlet implementation class AddNewIngredientServlet
 */
@WebServlet("/AddNewIngredientServlet")
public class AddNewIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewCocktailBean newCBean = new NewCocktailBean();
	private NewCocktailController contr = new NewCocktailController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewIngredientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException { 
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((request.getParameter("ingredientN") != null)&&(request.getParameter("ingredientQ") != null)&&(request.getParameter("cap") != null)){
        	String n = (String)request.getParameter("ingredientN");
        	CheckIngredientBean newIngBean = new CheckIngredientBean(n, request.getParameter("ingredientQ"));
        	try {
        		contr.insertedIngredientIsValid(newIngBean);
        	} catch (NumberFormatException e) {
        		request.getSession().setAttribute("newPostMessage", "Ingredient's quantity must be a number.");
        		response.sendRedirect(request.getHeader("Referer"));
        		return;
        	} catch (StringIsEmptyException e) {
        		request.getSession().setAttribute("newPostMessage", e.getMessage());
        		response.sendRedirect(request.getHeader("Referer"));
        		return;
        	}
        	
        	float q = Float.parseFloat(request.getParameter("ingredientQ"));
        	int t = 0;
        	String tag = request.getParameter("cap");
            if(tag.equals("ml")) {
            	t = 2; //ml
            }
            else if(tag.equals("g")) {
            	t = 1; //g
            }
            else {
            	t = 0; //none (float)
            }
        	this.newCBean.getRecipe().addIngredientBean(n, q, t);
        	
        }
		else {
			System.out.println("fuori ingr");
		}
		
		if ((request.getParameter("tag") != "")&&(request.getParameter("tag") != null)){
			System.out.println("dentro tag");
			String t = request.getParameter("tag");
			CheckTagBean newTagBean = new CheckTagBean(t);
			try {
				contr.insertedTagIsValid(newTagBean);
			} catch (StringIsEmptyException e) {
				request.getSession().setAttribute("",e.getMessage());
				return;
			}
			
			this.newCBean.addTagBean(t);
			response.sendRedirect(request.getHeader("Referer"));
			return;
        }
		else {
			System.out.println("fuori tag");
			//request.getSession().setAttribute("newPostMessage", "Input not valid");
		}
		
		request.getSession().setAttribute("bean",this.newCBean);
		request.getSession().setAttribute("controller",contr);
		response.sendRedirect(request.getHeader("Referer"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//va su done =========================
		NewCocktailBean b = (NewCocktailBean)request.getSession().getAttribute("bean");
		NewCocktailController controller = (NewCocktailController)request.getSession().getAttribute("controller");
				if((request.getParameter("cocktailName") != null)&&(request.getParameter("procedure") != null)) {
					String cocktailName = request.getParameter("cocktailName");
					String cocktailProcedure = request.getParameter("procedure");
					b.setName(cocktailName);
			    	b.getRecipe().setProcedure(cocktailProcedure);
					b.setImage("image/tick.png");
					System.out.println("sono qui\n" + cocktailName + ", " + cocktailProcedure);
					System.out.println("tag-> " + newCBean.getTags().get(0));
			    	
			    	try {
			    		controller.insertedCocktailIsValid(newCBean);
			    	} catch (StringIsEmptyException | IngredientListIsEmptyException e) {
			    		request.getSession().setAttribute("newPostMessage", e.getMessage());
			    		response.sendRedirect(request.getHeader("Referer"));
			    		return;
			    	} 
					
			    	String nextJSP = "/Homepage.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
					dispatcher.forward(request,response);
				}
				else {
					response.sendRedirect(request.getHeader("Referer"));
				}
	}

}
