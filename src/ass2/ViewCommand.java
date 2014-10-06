package ass2;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();
			
			String viewAllMovies =  request.getParameter("viewAllMovies");
			String viewDetail =  request.getParameter("viewDetail");
			

			if (viewAllMovies != null) {
				ArrayList<MovieBean> allMovies = new ArrayList<MovieBean>(); 
				
				ResultSet resultMovies= stmt.executeQuery("SELECT * FROM movies");
				
				while(resultMovies.next()){
					MovieBean newBean = new MovieBean();
					
					//GET ALL THE VALUES
					String dbID = resultMovies.getString("movieid");
					String dbTitle = resultMovies.getString("title");
					String dbPoster = resultMovies.getString("poster");
					String dbDirector = resultMovies.getString("director");
					String dbSypnosis = resultMovies.getString("sypnosis");
					String dbAgeRating = resultMovies.getString("agerating");
					String dbReleaseDate = resultMovies.getString("releasedate");
					
					//CONVERT SEVERAL VALUES
					int intID = Integer.parseInt(dbID);
					
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        Date parsed = format.parse(dbReleaseDate);
			        java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());
			        
			        //GET THE GENRES & SET TO THE BEAN
			        Statement stmt2 = conn.createStatement();
			        String getGenreQuery = "SELECT name AS genreName from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = "+intID;
			        ResultSet resultGenre= stmt2.executeQuery(getGenreQuery);
			        while(resultGenre.next()){
			        	newBean.setGenre(resultGenre.getString("genreName"));
			        }
			        
			        //SET INTO THE NEWBEAN
					newBean.setMovieID(intID);
					newBean.setTitle(dbTitle);
					newBean.setPoster(dbPoster);
					newBean.setDirector(dbDirector);
					newBean.setSypnosis(dbSypnosis);
					newBean.setAgeRating(dbAgeRating);
					newBean.setReleaseDate(convertedDate);
					
					//ADD THE BEAN TO THE LIST
					allMovies.add(newBean);
				}
				
				
				request.getSession().setAttribute("allMovies", allMovies);
			}
			else if(viewDetail != null){
				String movieID = request.getParameter("movieid");
				MovieBean newBean = new MovieBean();
				
				ResultSet resultDetail= stmt.executeQuery("SELECT * FROM movies WHERE movieid = "+ movieID);
				
				if(resultDetail.next()){
					
					//GET ALL THE VALUES
					String dbID = resultDetail.getString("movieid");
					String dbTitle = resultDetail.getString("title");
					String dbPoster = resultDetail.getString("poster");
					String dbDirector = resultDetail.getString("director");
					String dbSypnosis = resultDetail.getString("sypnosis");
					String dbAgeRating = resultDetail.getString("agerating");
					String dbReleaseDate = resultDetail.getString("releasedate");
					
					//CONVERT SEVERAL VALUES
					int intID = Integer.parseInt(dbID);
					
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        Date parsed = format.parse(dbReleaseDate);
			        java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());
			        
			        //GET THE GENRES & SET TO THE BEAN
			        Statement stmt2 = conn.createStatement();
			        String getGenreQuery = "SELECT name AS genreName from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = "+intID;
			        ResultSet resultGenre= stmt2.executeQuery(getGenreQuery);
			        while(resultGenre.next()){
			        	newBean.setGenre(resultGenre.getString("genreName"));
			        }
			        
			        //SET INTO THE NEWBEAN
					newBean.setMovieID(intID);
					newBean.setTitle(dbTitle);
					newBean.setPoster(dbPoster);
					newBean.setDirector(dbDirector);
					newBean.setSypnosis(dbSypnosis);
					newBean.setAgeRating(dbAgeRating);
					newBean.setReleaseDate(convertedDate);
					
					request.getSession().setAttribute("movieDetail", newBean);
				}
			}
			
			conn.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	

	
	private Connection conn;
	private Statement stmt;
}
