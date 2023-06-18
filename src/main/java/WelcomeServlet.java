

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		if(isNumeric(request.getParameter("count"))  && (!request.getParameter("count").isEmpty())) {
		int count = Integer.parseInt(request.getParameter("count"));
		if( 2<=count || count>20 ) {HttpSession session = request.getSession();
		session.setAttribute("count", count);
		response.sendRedirect("Welcome.jsp");
		}else {
			
			out.println("<div> <p style=\"font-size:25px\"> Please Enter A Digit Number! (2<)</p> </div>");
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}}
		else {
			out.println("<div> <p style=\"font-size:25px\"> Please Enter A Digit Number! (2<)</p> </div>");
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			
		}
		
		
	}
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
