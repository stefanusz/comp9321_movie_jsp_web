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
	
				String movieTitle = request.getParameter("movieTitle");
				String poster = request.getParameter("poster");
				String actor = request.getParameter("actor");
				//TO DO
				String[] genres = request.getParameterValues("genre");
				String director = request.getParameter("director");
				String sypnosis = request.getParameter("sypnosis");
				String ageRating = request.getParameter("ageRating");
				
				
				
			}
			else if(addCinema != null){
				String cinemaName = request.getParameter("cinemaName");
				String location = request.getParameter("location");
				String capacity = request.getParameter("capacity");
				int capacityInt;
				
				//CHECK IF ITS INTEGER
				try { 
					capacityInt = Integer.parseInt(capacity);
					String insertQuery = "INSERT INTO cinema VALUES (DEFAULT,'"+cinemaName+"','"+location+"',"+capacityInt+")";
					stmt.execute(insertQuery);
			    } catch(NumberFormatException e) { 
			        return false;
			    }
			}
			
			conn.close();
			return true;
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		
		return true;
	}
	private Connection conn;
	private Statement stmt;
}
