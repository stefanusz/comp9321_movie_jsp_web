package ass2;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControlServlet
 */
@WebServlet({ "/ConstrolServlet", "/control" })
@MultipartConfig
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        commandMap = new HashMap<String,Command>();
        commandMap.put("register", new RegisterCommand());
        commandMap.put("add", new AddCommand());
        commandMap.put("doLogin", new Login());
        commandMap.put("edit", new EditCommand());
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
		String register  =  request.getParameter("register");
		String addMovies =  request.getParameter("addMovies");
		
		String addCinema =  request.getParameter("addCinema");
		String addAmenities =  request.getParameter("addAmenities");
		String addActor =  request.getParameter("addActor");
		String doLogin   = 	request.getParameter("login");
		String editProfile =  request.getParameter("editProfile");
		
		String nextPage = "index.jsp";
		String prevPage = "";
		String message = "";
		boolean isSuccess = true;

		
		
		if(doLogin != null){
			Command command = commandMap.get("doLogin");
			isSuccess = command.execute(request,response);
			nextPage = "index.jsp";
		}
		

		if(register != null){
			prevPage = "register.jsp";
			Command command = commandMap.get("register");
			isSuccess = command.execute(request,response);
		}
		else if(addMovies != null){
			prevPage = "addMovies.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
		}
		else if(addCinema != null){
			prevPage = "addCinema.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
		}
		else if(addAmenities != null){
			prevPage = "addAmenities.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
		}
		else if(addActor != null){
			prevPage = "addActor.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
		}
		else if(editProfile != null){
			prevPage = "editProfile.jsp";
			Command command = commandMap.get("edit");
			isSuccess = command.execute(request,response);
		}
		
		
		if(isSuccess){
			message = "Success!";
			request.getSession().setAttribute("message", message);
			nextPage = "successPage.jsp";
		}
		else{
			message = "An error occurred!";
			request.getSession().setAttribute("message", message);
			nextPage = prevPage;
		}
		
		response.sendRedirect(nextPage);
		
	}
	
	private HashMap<String, Command> commandMap; 

}
