package ass2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		String addMovies= (String) request.getParameter("addMovies");

		if(addMovies != null){

			String movieTitle = (String) request.getParameter("movieTitle");
			String poster = (String) request.getParameter("poster");
			String actor = (String) request.getParameter("actor");
			
			String[] genres = request.getParameterValues("genre");
			String director = (String) request.getParameter("director");
			String sypnosis = (String) request.getParameter("sypnosis");
			String ageRating = (String) request.getParameter("ageRating");
			
			
			
		}
		
		return false;
	}

}
