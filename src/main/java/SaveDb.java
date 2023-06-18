

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class Deneme
 */
public class SaveDb extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> allInputs = new ArrayList<>();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session.getAttribute("allInputs")!=null) {
	int count= (int)session.getAttribute("count");
	allInputs = (ArrayList<String>)session.getAttribute("allInputs");
	response.setContentType("text/html");
	MongoDbConnection insertDb= new MongoDbConnection();
	String outputs= (String)session.getAttribute("sonuc1");
	long time = (long)session.getAttribute("time");
	
	
insertDb.insertData(allInputs, outputs, time);
	//out.println("----"+count);
//out.println("----"+allInputs.size());
allInputs.clear();
session.removeAttribute("sonuc1");
session.removeAttribute("time");
for(int i =0; i<count;i++) {
	
	session.removeAttribute(String.valueOf(i));	
}
out.println("<div> <p style=\"font-size:25px\" style=\"font: bold;\"> Saved Succesfully!</p> </div>");
RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
rd.include(request, response);
	}
		else {
			out.println("<div> <p style=\"color:red;\" style=\"font-size:25px\" style=\"font: bold;\" > Register Failure!</p> </div>");
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.include(request, response);
		}
	}
	
	
}
