package ass2;

import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();
		
			String addMovies= (String) request.getParameter("addMovies");
			String addCinema= (String) request.getParameter("addCinema");
	
			if(addMovies != null){
	
				String movieTitle = (String) request.getParameter("movieTitle");
				String poster = (String) request.getParameter("poster");
				String actor = (String) request.getParameter("actor");
				//TO DO
				String[] genres = request.getParameterValues("genre");
				String director = (String) request.getParameter("director");
				String sypnosis = (String) request.getParameter("sypnosis");
				String ageRating = (String) request.getParameter("ageRating");
				
				
				
			}
			else if(addCinema != null){
				String cinemaName = (String) request.getParameter("cinemaName");
				String location = (String) request.getParameter("location");
				String capacity = (String) request.getParameter("capacity");
				int capacityInt;
				
				//CHECK IF ITS INTEGER
				try { 
					capacityInt = Integer.parseInt(capacity);
			    } catch(NumberFormatException e) { 
			        return false; 
			    }
				stmt.execute("INSERT INTO cinema VALUES (DEFAULT,'"+cinemaName+"','"+location+"+','"+capacityInt+"')");
			}
			
			
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
	private Connection conn;
	private Statement stmt;
}
