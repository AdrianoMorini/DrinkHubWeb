package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.LoginBean;
import logic.controller.CocktailPostController;
import logic.controller.LoginController;
import logic.controller.SponsorController;

/**
 * Servlet implementation class LoginBean
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		LoginController controller = LoginController.getInstance();
		LoginBean bean = controller.getBean();
		bean.setUsername(user);
		bean.setPassword(pass);
		if(controller.findIdentity()) {
			String name = controller.getBean().getName();
			request.getSession().setAttribute("name",name);
			System.out.println(request.getSession().getAttribute("name"));
			SponsorController contr = SponsorController.getInstance();
	    	contr.cleanSponsorDB();
	    	CocktailPostController con = CocktailPostController.getInstance();
	    	con.findSponsoredCocktail();
	    	if(con.getBean().getPostList() == null) {
	        	//scrivere 'no posts' e poi refresh della pagina
	    		request.getSession().setAttribute("postMessage","NO POSTS");
	    		String nextJSP = "/Homepage.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
	            return;
	        }
	    	else {
	    		request.getSession().setAttribute("len",con.getBean().getPostList().size());
	    		request.getSession().setAttribute("listSponsor",con.getBean().getPostList());
	    	}
			String nextJSP = "/Homepage.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else {
			System.out.println("ERROR");
			request.getSession().setAttribute("message", "Error. Please try again.");
			response.sendRedirect(request.getHeader("Referer"));
		}
	}

}
