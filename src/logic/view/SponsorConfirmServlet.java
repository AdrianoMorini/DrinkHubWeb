package logic.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SponsorConfirmServlet
 */
@WebServlet("/SponsorConfirmServlet")
public class SponsorConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorConfirmServlet() {
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
			String price = (String) request.getSession().getAttribute("pricePreview");
			System.out.println("quindi il finale è: " + price);
			if(price.equals("")) {
				request.getSession().setAttribute("pricePreview","Something goes wrong. Try again.");
				response.sendRedirect(request.getHeader("Referer"));
			}
			else {
				String nextJSP = "/prePayPage.jsp";
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
