package ass2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search implements Command {

	private Connection conn;
	private Statement stmt;

	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {

		String search = request.getParameter("search");

		if (search != null) {

			

			try {
				conn = DBConnectionFactory.getConnection();
				stmt = conn.createStatement();

				
				String sqlQuery = "SELECT DISTINCT m.movieID, m.title, m.poster, m. FROM (SELECT * from movies) as m, " +
						"(SELECT * FROM genre) as g, (SELECT * FROM resolveGenre) as rg " +
						"WHERE m.title LIKE '%"+search+ "%' OR g.name LIKE '"+search+"%'";
				ResultSet result = stmt.executeQuery(sqlQuery);
				
				ArrayList <MovieBean> finalResult = new ArrayList<MovieBean>();
				//title, small version of the poster art, genre, main actors, rating
				while (result.next()){
					int movieID = result.getInt("movieID");
					
					MovieBean movies = new MovieBean();
					
					movies.setMovieID(movieID);
					movies.setTitle(result.getString("title"));
					movies.setPoster(result.getString("poster"));
					movies.setDirector(result.getString("director"));
					movies.setAgeRating(result.getString("ageRating"));

					
			        String getActorQuery = "SELECT name FROM resolveactor r JOIN actor a ON a.actorid = r.actorid WHERE movieid = "+movieID;
			        ResultSet result2 = stmt.executeQuery(getActorQuery);
			        
			        while(result2.next()){
			        	
			        	String actorName = result2.getString("name");
			        	movies.setActor(actorName);
			        	
			        }
			        
			        finalResult.add(movies);
					
				}

				return true;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
