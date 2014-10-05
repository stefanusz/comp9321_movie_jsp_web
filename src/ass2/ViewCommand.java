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
			ArrayList<MovieBean> allMovies = new ArrayList<MovieBean>(); 
			
			ResultSet resultMovies= stmt.executeQuery("SELECT * FROM movies");
			
			while(resultMovies.next()){
				System.out.println("haha");
				MovieBean newBean = new MovieBean();
				
				//GET ALL THE VALUES
				String dbID = resultMovies.getString("movieid");
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
		        ArrayList<String> dbGenre = new ArrayList<String>();
		        String getGenreQuery = "SELECT name from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = "+intID;
		        ResultSet resultGenre= stmt.executeQuery(getGenreQuery);
		        while(resultGenre.next()){
		        	newBean.setGenre(resultGenre.getString("name"));
		        }
		        
		        //SET INTO THE NEWBEAN
				newBean.setBeanID(intID);
				newBean.setPoster(dbPoster);
				newBean.setDirector(dbDirector);
				newBean.setSypnosis(dbSypnosis);
				newBean.setAgeRating(dbAgeRating);
				newBean.setReleaseDate(convertedDate);
				
				//ADD THE BEAN TO THE LIST
				allMovies.add(newBean);
			}
			
			
			request.getSession().setAttribute("allMovies", allMovies);
			
			
			conn.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	

	
	private Connection conn;
	private Statement stmt;
}