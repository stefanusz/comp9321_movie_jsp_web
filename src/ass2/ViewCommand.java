package ass2;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmtGenre = conn.createStatement();
			stmtMovies = conn.createStatement();
			stmtActor = conn.createStatement();
			stmtComment = conn.createStatement();
			stmtCinema = conn.createStatement();
			stmtTimes = conn.createStatement();
			stmtNowShowing = conn.createStatement();
			stmtComingSoon = conn.createStatement();
			stmtRating = conn.createStatement();
			stmtBooking = conn.createStatement();
			
			String viewAllMovies =  request.getParameter("viewAllMovies");
			String viewDetail =  request.getParameter("viewDetail");
			String getShowTimes = request.getParameter("getShowTimes");
			String viewBooking = request.getParameter("viewBooking");
			

			if (viewAllMovies != null) {
				ArrayList<MovieBean> allMovies = new ArrayList<MovieBean>(); 
				
				ResultSet resultMovies= stmtMovies.executeQuery("SELECT * FROM movies");
				
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
			        String getGenreQuery = "SELECT name AS genreName from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = "+intID;
			        ResultSet resultGenre= stmtGenre.executeQuery(getGenreQuery);
			        while(resultGenre.next()){
			        	newBean.setGenre(resultGenre.getString("genreName"));
			        }
			        
			        //GET THE ACTORS & SET TO THE BEAN
			        String getActorQuery = "SELECT name FROM resolveactor r JOIN actor a ON a.actorid = r.actorid WHERE movieid = "+intID;
			        ResultSet resultActor= stmtActor.executeQuery(getActorQuery);
			        while(resultActor.next()){
			        	newBean.setActor(resultActor.getString("name"));
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
				MovieBean movieDetail = new MovieBean();
				ArrayList<CommentBean> movieComment = new ArrayList<CommentBean>();
				
				ResultSet resultDetail= stmtMovies.executeQuery("SELECT * FROM movies WHERE movieid = "+ movieID);
				
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
			        String getGenreQuery = "SELECT name AS genreName from resolvegenre r JOIN genre g ON g.genreid = r.genreid WHERE movieid = "+intID;
			        ResultSet resultGenre= stmtGenre.executeQuery(getGenreQuery);
			        while(resultGenre.next()){
			        	movieDetail.setGenre(resultGenre.getString("genreName"));
			        }
			        
			        //GET THE ACTORS & SET TO THE BEAN
			        String getActorQuery = "SELECT name FROM resolveactor r JOIN actor a ON a.actorid = r.actorid WHERE movieid = "+intID;
			        ResultSet resultActor= stmtActor.executeQuery(getActorQuery);
			        while(resultActor.next()){
			        	movieDetail.setActor(resultActor.getString("name"));
			        }
			        
			        //SET INTO THE NEWBEAN
			        movieDetail.setMovieID(intID);
			        movieDetail.setTitle(dbTitle);
			        movieDetail.setPoster(dbPoster);
			        movieDetail.setDirector(dbDirector);
			        movieDetail.setSypnosis(dbSypnosis);
			        movieDetail.setAgeRating(dbAgeRating);
			        movieDetail.setReleaseDate(convertedDate);
					
					//GET THE COMMENTS
			        String getCommentQuery = "SELECT username,comment,rating FROM comment c JOIN movies m ON c.movieid = m.movieid JOIN users u ON c.userid = u.userid WHERE m.movieid ="+movieID;
			        ResultSet resultComment= stmtComment.executeQuery(getCommentQuery);
			        while(resultComment.next()){
			        	CommentBean newCommentBean = new CommentBean();
			        	newCommentBean.setUser(resultComment.getString("username"));
			        	newCommentBean.setComment(resultComment.getString("comment"));
			        	newCommentBean.setRating(resultComment.getDouble("rating"));
			        	movieComment.add(newCommentBean);
			        }
			        
			        //GET CINEMA TIMES FOR ADMIN
			        ArrayList<CinemaBean> movieEmptyTimes = new ArrayList<CinemaBean>();
			        String getCinemaQuery = "SELECT * FROM cinema";
			        ResultSet resultCinema= stmtCinema.executeQuery(getCinemaQuery);
			        while(resultCinema.next()){
			        	CinemaBean newCinemaBean = new CinemaBean();
			        	int cinemaID = resultCinema.getInt("cinemaid");
			        	String cinemaName = resultCinema.getString("name");
			        	newCinemaBean.setCinemaID(cinemaID);
			        	newCinemaBean.setName(cinemaName);
			        	
				        String getTimesQuery = "SELECT time FROM resolvemovies rm JOIN showtimes s ON rm.resolvemoviesid = s.resolvemoviesid WHERE rm.cinemaid = "+cinemaID+"AND rm.movieid="+movieID;
				        ResultSet resultTimes= stmtTimes.executeQuery(getTimesQuery);
				        while(resultTimes.next()){
				        	String time = resultTimes.getString("time");
				        	newCinemaBean.removeShowTimes(time);
				        }
				        movieEmptyTimes.add(newCinemaBean);
			        }
					
			        
			        //BOOKING
			        if(getShowTimes != null){
			        	String bookingDate = request.getParameter("bookingDate");
			        	if(!bookingDate.equals("")){
			        		ArrayList<CinemaBean> bookingTimes = new ArrayList<CinemaBean>();
				        	
				        	//GET CINEMA AND AVAILABLE TIMES
					        getCinemaQuery = "SELECT * FROM cinema";
					        resultCinema= stmtCinema.executeQuery(getCinemaQuery);
					        while(resultCinema.next()){
					        	CinemaBean newCinemaBean = new CinemaBean();
					        	newCinemaBean.clearShowTimes();
					        	
					        	int cinemaID = resultCinema.getInt("cinemaid");
					        	String cinemaName = resultCinema.getString("name");
					        	
					        	newCinemaBean.setCinemaID(cinemaID);
					        	newCinemaBean.setName(cinemaName);
					        	
						        String getTimesQuery = "SELECT time FROM resolvemovies rm JOIN showtimes s ON rm.resolvemoviesid = s.resolvemoviesid WHERE rm.cinemaid = "+cinemaID+"AND rm.movieid="+movieID;
						        ResultSet resultTimes= stmtTimes.executeQuery(getTimesQuery);
						        while(resultTimes.next()){
						        	String time = resultTimes.getString("time");
						        	newCinemaBean.setShowTimes(time);
						        }
						        bookingTimes.add(newCinemaBean);
					        }
					        request.setAttribute("bookingTimes", bookingTimes);
					        request.setAttribute("bookingDate", bookingDate);
			        	}
			        	
			        }
			        
					request.getSession().setAttribute("movieDetail", movieDetail);
					request.getSession().setAttribute("movieComment", movieComment);
					request.getSession().setAttribute("movieEmptyTimes", movieEmptyTimes);
					
				}
				
				
				Date date = new Date();
				request.getSession().setAttribute("currentDate", date);
			}
			
			else if(viewBooking != null){
				String userID = (String) request.getSession().getAttribute("userid");
				
				ArrayList<BookingBean> myBooking = new ArrayList<BookingBean>();
				
				String getBookingQuery = "SELECT * FROM booking b JOIN showtimes s ON b.showtimeid=s.showtimeid JOIN resolvemovies rm ON s.resolvemoviesid=rm.resolvemoviesid JOIN cinema c ON rm.cinemaid=c.cinemaid JOIN movies m on rm.movieid=m.movieid WHERE b.userid="+userID+"ORDER BY bookingID";
		        ResultSet resultBooking= stmtBooking.executeQuery(getBookingQuery);
		        while(resultBooking.next()){
		        	BookingBean newBookingBean = new BookingBean();
		        	
		        	String cinemaName = resultBooking.getString("name");
		        	String movieTitle = resultBooking.getString("title");
		        	String bookingDate = resultBooking.getString("bookingDate");
		        	String time = resultBooking.getString("time");
		        	int noOfTicket = resultBooking.getInt("noofticket");
		        	
		        	newBookingBean.setCinemaName(cinemaName);
		        	newBookingBean.setMovieTitle(movieTitle);
		        	newBookingBean.setBookingDate(bookingDate);
		        	newBookingBean.setTime(time);
		        	newBookingBean.setNoOfTicket(noOfTicket);
		        	
		        	myBooking.add(newBookingBean);
		        }
		        request.setAttribute("myBooking", myBooking);
			}
			
			
			//VIEW NOW SHOWING AND COMING SOON IN THE INDEX PAGE
			ArrayList<MovieBean> nowShowing = new ArrayList<MovieBean>();
			ArrayList<MovieBean> comingSoon = new ArrayList<MovieBean>();
			
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");      
			String currentDate = df.format(date);
			
			//GET FOR NOW SHOWING
			int counter = 4;
			ResultSet resultNowShowing = stmtNowShowing.executeQuery("SELECT * FROM movies WHERE releasedate < '"+ currentDate+"' ORDER BY movieid DESC");
			
			while(resultNowShowing.next() && counter > 0){
				MovieBean newBean = new MovieBean();
				int dbMovieID = resultNowShowing.getInt("movieid");
				String dbTitle = resultNowShowing.getString("title");
				String dbPoster = resultNowShowing.getString("poster");
				String avgRatingString = "";
				double avgRatingDouble = 0;
				
				ResultSet resultRating = stmtRating.executeQuery("SELECT AVG(rating) AS averagerating FROM comment WHERE movieid ="+ dbMovieID);
				if(resultRating.next()){
					avgRatingDouble = resultRating.getDouble("averagerating");
			        DecimalFormat df2 = new DecimalFormat("#.##");
			        avgRatingString = df2.format(avgRatingDouble);
			        
			        if(avgRatingDouble == 0){
			        	avgRatingString ="N/A";
					}
				}
				
				newBean.setMovieID(dbMovieID);
				newBean.setTitle(dbTitle);
				newBean.setPoster(dbPoster);
				newBean.setRatingString(avgRatingString);
				newBean.setRatingDouble(avgRatingDouble);
				
				nowShowing.add(newBean);
				counter--;
			}
			//SORT BY RATING
			Collections.sort(nowShowing, new Comparator<MovieBean>() {
		        @Override public int compare(MovieBean b1, MovieBean b2) {
		            return (int) (b2.getRatingDouble() - b1.getRatingDouble());
		        }
		    });
			
			
			//GET FOR COMING SOON
			counter = 4;
			ResultSet resultComingSoon = stmtComingSoon.executeQuery("SELECT * FROM movies WHERE releasedate > '"+ currentDate+"' ORDER BY movieid DESC");
			while(resultComingSoon.next() && counter > 0){
				MovieBean newBean = new MovieBean();
				int dbMovieID = resultComingSoon.getInt("movieid");
				String dbTitle = resultComingSoon.getString("title");

				String dbPoster = resultComingSoon.getString("poster");
				String dbReleaseDate = resultComingSoon.getString("releasedate");

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date parsed = format.parse(dbReleaseDate);
			    java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());
			    
				String avgRatingString = "";
				double avgRatingDouble = 0;

				ResultSet resultRating = stmtRating.executeQuery("SELECT AVG(rating) AS averagerating FROM comment WHERE movieid ="+ dbMovieID);
				if(resultRating.next()){
					avgRatingDouble = resultRating.getDouble("averagerating");
			        DecimalFormat df2 = new DecimalFormat("#.##");
			        avgRatingString = df2.format(avgRatingDouble);
			        
			        if(avgRatingDouble == 0){
			        	avgRatingString ="N/A";
					}
				}
				newBean.setMovieID(dbMovieID);
				newBean.setTitle(dbTitle);
				newBean.setPoster(dbPoster);
				newBean.setRatingString(avgRatingString);
				newBean.setRatingDouble(avgRatingDouble);
				newBean.setReleaseDate(convertedDate);
				
				comingSoon.add(newBean);
				counter--;
			}
			//SORT BY RELEASE DATE
			Collections.sort(comingSoon, new Comparator<MovieBean>() {
				  public int compare(MovieBean b1, MovieBean b2) {
				      return b1.getReleaseDate().compareTo(b2.getReleaseDate());
				  }
			});
			
			request.getSession().setAttribute("nowShowing", nowShowing);
			request.getSession().setAttribute("comingSoon", comingSoon);
		
			
			conn.close();
			stmtGenre.close();
			stmtMovies.close();
			stmtActor.close();
			stmtComment.close();
			stmtCinema.close();
			stmtTimes.close();
			stmtNowShowing.close();
			stmtComingSoon.close();
			stmtRating.close();
			stmtBooking.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	

	
	private Connection conn;
	private Statement stmtGenre;
	private Statement stmtMovies;
	private Statement stmtActor;
	private Statement stmtComment;
	private Statement stmtCinema; 
	private Statement stmtTimes;
	private Statement stmtNowShowing;
	private Statement stmtComingSoon;
	private Statement stmtRating;
	private Statement stmtBooking;
}
