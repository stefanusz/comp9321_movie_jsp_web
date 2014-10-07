package ass2;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
					String dbPoster = request.getContextPath()+"/"+resultMovies.getString("poster");
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
			        
			        System.out.println(dbPoster);
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
				
				
				Date date = new Date();
				request.getSession().setAttribute("currentDate", date);
			}
			else{ //VIEW NOW SHOWING AND COMING SOON IN THE INDEX PAGE
				
				ArrayList<MovieBean> nowShowing = new ArrayList<MovieBean>();
				ArrayList<MovieBean> comingSoon = new ArrayList<MovieBean>();
				
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");      
				String currentDate = df.format(date);
				
				int counter = 4;
				Statement stmtNowShowing = conn.createStatement();
				ResultSet resultNowShowing = stmtNowShowing.executeQuery("SELECT * FROM movies WHERE releasedate < '"+ currentDate+"' ORDER BY movieid DESC");
				//resultNowShowing.afterLast();
				while(resultNowShowing.next() && counter > 0){
					MovieBean newBean = new MovieBean();
					int dbMovieID = resultNowShowing.getInt("movieid");
					String dbTitle = resultNowShowing.getString("title");
					String dbPoster = resultNowShowing.getString("poster");
					double averageRating = 0;

					ResultSet resultRating = stmt.executeQuery("SELECT AVG(rating) AS averageRating FROM comment WHERE movieid ="+ dbMovieID);
					if(resultRating.next()){
						averageRating = resultRating.getDouble("averageRating");
				        //DecimalFormat df2 = new DecimalFormat("#.##");
				        //averageRating = df2.format(averageRating);
				        //System.out.print(averageRating);
					}
					newBean.setMovieID(dbMovieID);
					newBean.setTitle(dbTitle);
					newBean.setPoster(dbPoster);
					newBean.setRating(averageRating);
					
					nowShowing.add(newBean);
					counter--;
				}
				
				counter = 4;
				Statement stmtComingSoon = conn.createStatement();
				ResultSet resultComingSoon = stmtComingSoon.executeQuery("SELECT * FROM movies WHERE releasedate > '"+ currentDate+"' ORDER BY movieid DESC");
				//resultComingSoon.afterLast();
				while(resultComingSoon.next() && counter > 0){
					MovieBean newBean = new MovieBean();
					int dbMovieID = resultNowShowing.getInt("movieid");
					String dbTitle = resultNowShowing.getString("title");
					String dbPoster = resultNowShowing.getString("poster");
					String dbReleaseDate = resultNowShowing.getString("releasedate");
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				    Date parsed = format.parse(dbReleaseDate);
				    java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());
				    
					double averageRating = 0;

					ResultSet resultRating = stmt.executeQuery("SELECT AVG(rating) AS averageRating FROM comment WHERE movieid ="+ dbMovieID);
					if(resultRating.next()){
						averageRating = resultRating.getDouble("averageRating");
				        //DecimalFormat df2 = new DecimalFormat("#.##");
				        //averageRating = df2.format(averageRating);
				        //System.out.print(averageRating);
					}
					newBean.setMovieID(dbMovieID);
					newBean.setTitle(dbTitle);
					newBean.setPoster(dbPoster);
					newBean.setRating(averageRating);
					newBean.setReleaseDate(convertedDate);
					
					
					comingSoon.add(newBean);
					counter--;
				}
				
				request.getSession().setAttribute("nowShowing", nowShowing);
				request.getSession().setAttribute("comingSoon", comingSoon);
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
