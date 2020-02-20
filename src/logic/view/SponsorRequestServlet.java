package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controller.SponsorController;

/**
 * Servlet implementation class SponsorRequestServlet
 */
@WebServlet("/SponsorRequestServlet")
public class SponsorRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(request.getSession().getAttribute("name") == null) {
			//not logged
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request,response);
			return;
		}
		else {
			//logged
			String type = request.getParameter("type");
			String time = request.getParameter("time");
	    
			if((type == null)||(time == null)) {
				request.getSession().setAttribute("pricePreview","Input not valid. Please select values.");
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
		
			SponsorController controller = SponsorController.getInstance();
			controller.getBean().setType(type);
			controller.getBean().setTime(time);
    	
			controller.calculatePrice();
		
			if(controller.getBean().getPrice() != 0) 
			{
				String t = String.valueOf(controller.getBean().getPrice());
				request.getSession().setAttribute("pricePreview", t);
    		
				controller.getBean().setType("");
			}
			else {
				request.getSession().setAttribute("pricePreview", "INPUT NOT VALID");
			}
	    
			response.sendRedirect(request.getHeader("Referer"));
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
