package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ProfileBean;
import logic.controller.CocktailPostController;
import logic.controller.LoginController;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("name") == null) {
			//not logged
			System.out.println("not logged");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request,response);
			return;
		}
		else {
			//logged
			System.out.println("logged as " + request.getSession().getAttribute("name"));
			
			LoginController controller = LoginController.getInstance();
	    	
	    	ProfileBean.setUsername(controller.getBean().getUsername());
	    	ProfileBean.setUName(controller.getBean().getName());
	    	ProfileBean.setSurname(controller.getBean().getSurname());
	    	ProfileBean.setImage(controller.getBean().getImage());
			
			String usernameProfile = ProfileBean.getUsername();
			String name = ProfileBean.getName();
			String surname = ProfileBean.getSurname();
			String image = ProfileBean.getImage();

			request.getSession().setAttribute("usernameBean", usernameProfile);
			request.getSession().setAttribute("nameBean", name);
			request.getSession().setAttribute("surnameBean", surname);
			request.getSession().setAttribute("imageBean", image);
			
			
			CocktailPostController con = CocktailPostController.getInstance();
        	con.getBean().setUsername(usernameProfile);
        	con.findCocktailList();
        	
        	if(con.getBean().getPostList() == null) {
            	//scrivere 'no posts' e poi refresh della pagina
        		request.getSession().setAttribute("profileMessage","NO POSTS");
        		String nextJSP = "/profile.jsp";
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    			dispatcher.forward(request,response);
                return;
            }
        	else {
        		request.getSession().setAttribute("length",con.getBean().getPostList().size());
        		request.getSession().setAttribute("list",con.getBean().getPostList());
        	}
        	//request.getSession().setAttribute("getPostList",con.getBean().getPostList().get(0).getName());
			
			
			
			
			String nextJSP = "/profile.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
	}

}
