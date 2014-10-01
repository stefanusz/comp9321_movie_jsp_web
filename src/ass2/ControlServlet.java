package ass2;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControlServlet
 */
@WebServlet({ "/ConstrolServlet", "/control" })
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        commandMap = new HashMap<String,Command>();
        commandMap.put("register", new RegisterCommand());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String register = (String) request.getParameter("register");
		
		if(register != null){
			
			String message = "a fail";
			Command command = commandMap.get("register");
			boolean registered = command.execute(request,response);
			
			
			System.out.println(registered);
			if(registered){
				message = "";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("index.jsp");
				
			}
			else{
				
				request.getSession().setAttribute("message", message);
				response.sendRedirect("register.jsp");
			
			}
		}
		
		
		//RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
		//rd.forward(request, response);
	}
	
	private HashMap<String, Command> commandMap; 

}
