package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controller.LoginController;
import logic.controller.SponsorController;

/**
 * Servlet implementation class SponsorServlet
 */
@WebServlet("/SponsorServlet")
public class SponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			SponsorController controller = SponsorController.getInstance();
			
			String usernameSponsor = LoginController.getInstance().getBean().getUsername();
			
			controller.getBean().setUserSponsor(usernameSponsor);
			controller.searchExistingSponsor();
			
			if( controller.getBean().getTimeSponsor() != null) {
				System.out.println("sponsorizzazione esistente");
				request.getSession().setAttribute("timeline",controller.getBean().getTimeSponsor());
				String nextJSP = "/noSponsorPage.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
			}
			
			else {
				System.out.println("sponsorizzazione da fare");
				String nextJSP = "/sponsorPage.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
