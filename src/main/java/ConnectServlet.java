

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class ConnectServlet
 */
public class ConnectServlet extends HttpServlet {
	ArrayList<String> allInputs = new ArrayList<>();
	long start = System.currentTimeMillis();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.removeAttribute("time");
		session.removeAttribute("sonuc1");
		session.removeAttribute("allInputs");
		response.setContentType("text/html");
      // out.println(session.getAttribute("count")+request.getParameter("0"));
       int count =(int)session.getAttribute("count");
       System.out.println(count);
       allInputs.clear();
		for(int i=0; i<count;i++) {
			String s=(String)request.getParameter(String.valueOf(i));
			/*if(request.getParameter(String.valueOf(i))==null)
				s="xxx";
			*/session.setAttribute(String.valueOf(i),s.trim());
			allInputs.add(request.getParameter(String.valueOf(i)).trim());
			System.out.println(allInputs.get(i));
		
		if(!allInputs.get(i).matches(".*[a-zA-Z]+.*")) {
			session.removeAttribute("allInputs");
			out.println("<div> <p style=\"color:red;\" style=\"font-size:25px\" style=\"font: bold;\" > Check Fields!</p> </div>");
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.include(request, response);
			allInputs.clear();
			break;
		}
		}
		
		//timer
		
		
		
		String ana_metin = "";
		ArrayList<String> strings=new ArrayList<String>();
		
		strings=allInputs;
		
		String[] metinler=new String[strings.size()];
		String[][] kelimelere_bolunmus=new String[metinler.length][];
		for(int i=0;i<metinler.length;i++) {
			metinler[i]=strings.get(i);
			kelimelere_bolunmus[i]=metinler[i].split(" ");
			//System.out.println(kelimelere_bolunmus[i][0]);
		}
		
		
		
		String ana_metin_kelimeler="";
		
		if(kontrol(allInputs))
			//kelime
		{
			String ara_metin="";
			
			for(int i =0; i<strings.size();i++)
			{
				String str1= kelimelere_bolunmus[i][0];
			String str2;
			if(i!=(strings.size()-1))
			str2= kelimelere_bolunmus[i+1][0];
			else
				break;
				//str2=" ";
			
			
			char[] char_metin1 = str1.toCharArray();
			char[] char_metin2 = str2.toCharArray();
			int kucuk_metin;
			if(char_metin1.length>char_metin2.length) 
				kucuk_metin=char_metin2.length;
			else 
				kucuk_metin=char_metin1.length;
			
			for(int j=0;j<kucuk_metin;j++) {
				if(char_metin1[j]!=char_metin2[j]) {
					ara_metin=ara_metin+"";
						break;
				}
				else {
				ara_metin=ara_metin+char_metin1[j];
				}
				
			}
			System.out.println(ara_metin);
			if(ara_metin.equals("")&& i>=1) {
				ana_metin_kelimeler = ana_metin_kelimeler+  " "+ str2;
				
			}
			else if(ara_metin.equals("")){
				ana_metin_kelimeler = ana_metin_kelimeler+ " " +str1+ " "+ str2;
			}
				
			else {
				if(str2.contains(str1)) {
					ana_metin_kelimeler = ana_metin_kelimeler + " " + str2;
				}
				String replacing=str2.replaceAll(ara_metin, "");
				ana_metin_kelimeler= ana_metin_kelimeler+ " " +str1+ replacing;
			}
			ara_metin="";
			}
			
			System.out.println("--------------------"+ana_metin_kelimeler);
			
			
			
		}else {
			//cumleler
			
			for(int i=0;i<count-1;i++) { //2'nin yerine cumle sayisi gelecek
				for(int j=0;j<kelimelere_bolunmus[i].length;j++) {
					for(int k=0;k<kelimelere_bolunmus[i+1].length;k++) {
						if(!kelimelere_bolunmus[i+1][k].equals("x") && !kelimelere_bolunmus[i][j].equals("x")) { 
						if(kelimelere_bolunmus[i+1][k].contains(kelimelere_bolunmus[i][j]) ) {
							//System.out.println("doğru"+kelimelere_bolunmus[i+1][k]);
							if(kelimelere_bolunmus[i+1][k].length()>=kelimelere_bolunmus[i][j].length()) {
								ana_metin= ana_metin+kelimelere_bolunmus[i+1][k]+" ";
								kelimelere_bolunmus[i+1][k]="x";
								//System.out.println(kelimelere_bolunmus[i+1][k]);
							}else {
								ana_metin=ana_metin +kelimelere_bolunmus[i][j]+" ";
							}
							break;
						}else {
							ana_metin= ana_metin+""+kelimelere_bolunmus[i][j]+" ";
							break;
						}
					}
						else {k++;}
						}
					
				}
			}
			for(int i=0;i<kelimelere_bolunmus[(kelimelere_bolunmus.length)-1].length;i++) {
				if(!kelimelere_bolunmus[(kelimelere_bolunmus.length)-1][i].equals("x")) {
					ana_metin=ana_metin +kelimelere_bolunmus[(kelimelere_bolunmus.length)-1][i]+" ";
				}
			}
			
			
			/*for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					System.out.println(kelimelere_bolunmus[i][j]+" ");
				}
			}*/
			//kelimelere_bolunmus[0][0].charAt(0)==kelimelere_bolunmus[1][0].charAt(0)
			System.out.println(ana_metin);
			
		}
		
		
		
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		//float elapsedTimeSec = elapsedTimeMillis/1000F;//second 
		
		System.out.println(elapsedTimeMillis);
		
		
		
		
		
		
		
		
		
		if(count==allInputs.size()) {
		session.setAttribute("time", elapsedTimeMillis);
	    session.setAttribute("allInputs",allInputs);
	    session.removeAttribute("sonuc1");
	    session.setAttribute("sonuc1",(ana_metin + ana_metin_kelimeler));
		response.sendRedirect("Welcome.jsp");
		}
		
		
		
		
		
		}
	
	
	
	public boolean kontrol(ArrayList<String> inputs) {
		for(int i=0;i<inputs.size();i++) {
			if(inputs.get(i).contains(" ")) {
				return false;
			}
		}
		return true;
	}
	
}
