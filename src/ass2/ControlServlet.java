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
        commandMap.put("authenticate", new Authenticate());
        commandMap.put("edit", new EditCommand());
        commandMap.put("view", new ViewCommand());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewAllMovies = request.getParameter("viewAllMovies");
		String viewDetail = request.getParameter("viewDetail");
		
		String nextPage = "index.jsp";
		
		if(viewAllMovies != null){
			Command command = commandMap.get("view");
			command.execute(request, response);
			nextPage = "viewAllMovies.jsp";
		}
		else if(viewDetail != null){
			Command command = commandMap.get("view");
			command.execute(request, response);
			nextPage = "viewDetail.jsp";
		}
		//else{ //TO INDEX PAGE AT THE BEGINNING
			Command command = commandMap.get("view");
			command.execute(request, response);
			
		//}
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
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
		String addGenre=  request.getParameter("addGenre");
		String addComment=  request.getParameter("addComment");
		
		String logout   = 	request.getParameter("logout");
		String login   = 	request.getParameter("login");
		
		String editProfile =  request.getParameter("editProfile");
		
		String nextPage = "index.jsp";
		String prevPage = "";
		String message = "";
		boolean isSuccess = true;

		
		
		if((login != null) || (logout != null)){
			Command command = commandMap.get("authenticate");
			isSuccess = command.execute(request,response);
			nextPage = "index.jsp";
		}
		

		if(register != null){
			prevPage = "register.jsp";
			Command command = commandMap.get("register");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Successfully registered");
			nextPage = "index.jsp";
		}
		else if(addMovies != null){
			prevPage = "addMovies.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Movies successfully added");
			nextPage = "viewAllMovies.jsp";
			
			//REFRESH VIEWING ALL MOVIES
			command = commandMap.get("view");
			isSuccess = command.execute(request,response);
		}
		else if(addCinema != null){
			prevPage = "addCinema.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Cinema successfully added");
			nextPage = "index.jsp";
		}
		else if(addAmenities != null){
			prevPage = "addAmenities.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Amenities successfully added");
			nextPage = "index.jsp";
			
		}
		else if(addActor != null){
			prevPage = "addActor.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Actor successfully added");
			nextPage = "index.jsp";
		}
		else if(editProfile != null){
			prevPage = "editProfile.jsp";
			Command command = commandMap.get("edit");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Profile successfully edited");
			nextPage = "editProfile.jsp";
		}
		else if(addGenre != null){
			prevPage = "addGenre.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Genre successfully added");
			nextPage = "index.jsp";
		}
		else if(addComment != null){
			prevPage = "viewDetail.jsp";
			Command command = commandMap.get("add");
			isSuccess = command.execute(request,response);
			request.getSession().setAttribute("message", "Comment successfully added");
			nextPage = "viewDetail.jsp";
			
			//REFRESH VIEWING DETAIL AND NOWSHOWING
			command = commandMap.get("view");
			isSuccess = command.execute(request,response);
		}
		
		
		if(!isSuccess){
			message = "An error occurred!";
			request.getSession().setAttribute("message", message);
			nextPage = prevPage;
		}
		
		response.sendRedirect(nextPage);
		
	}
	
	
	public void checkSuccess(){
		
	}
	
	private HashMap<String, Command> commandMap; 

}
