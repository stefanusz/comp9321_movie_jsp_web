package ass2;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

@MultipartConfig
public class AddCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			conn = DBConnectionFactory.getConnection();
			stmtInsert = conn.createStatement();
			stmtActor = conn.createStatement();
			stmtActor2 = conn.createStatement();
			stmtCinema = conn.createStatement();
			stmtMovies = conn.createStatement();
			stmtResolveMovies = conn.createStatement();

			String addMovies = request.getParameter("addMovies");
			String addCinema = request.getParameter("addCinema");
			String addAmenities = request.getParameter("addAmenities");
			String addActor = request.getParameter("addActor");
			String addGenre = request.getParameter("addGenre");
			String addComment = request.getParameter("addComment");
			String addShowTimes = request.getParameter("addShowTimes");
			String addBooking = request.getParameter("addBooking");
			
			//***************************************************************
			//						ADD MOVIES	  
			//***************************************************************
			if (addMovies != null) {
				

				// START OF GETTING THE DIFFERENT VARIABLE FOR THE NORMAL FORM. 
				String movieTitle = request.getParameter("movieTitle");
				String actor = request.getParameter("actor");
				String[] genres = request.getParameterValues("genreid");
				String director = request.getParameter("director");
				String sypnosis = request.getParameter("sypnosis");
				String ageRating = request.getParameter("ageRating");
				String releaseDate= request.getParameter("releaseDate");
				// END OF GETTING THE DIFFERENT VARIABLE FOR THE FORM.
				
				movieTitle = movieTitle.toLowerCase();
				director = director.toLowerCase();
				sypnosis = sypnosis.toLowerCase();
				ageRating = ageRating.toLowerCase();
				
				movieTitle = movieTitle.replace("'", " ");
				director = director.replace("'", " ");
				sypnosis = sypnosis.replace("'", " ");
				ageRating = ageRating.replace("'", " ");
				
				String[] sypnosisArray = sypnosis.split(" ");
				
				//VALIDATION
			    if(movieTitle.equals("") || actor.equals("") || director.equals("") || sypnosis.equals("") || ageRating.equals("")||releaseDate.equals("")){
			    	request.getSession().setAttribute("message", "Please fill all fields");
					return false;
				}
			    else if(sypnosisArray.length > 100){
			    	request.getSession().setAttribute("message", "Sypnosis is over 100 words");
			    	return false;
			    }
			    else if(genres.length < 1){
			    	request.getSession().setAttribute("message", "Please choose genre type");
			    	return false;
			    }
			    //ResultSet result = stmt.executeQuery("SELECT USERNAME FROM users WHERE USERNAME = '"+username+"'");
				//if(result.next()){
			    //	return false;
				//}
			    
			    // TESTING UPLOAD OF FILE.
				Part filePart = request.getPart("poster");
				String fileName = getFileName(filePart);
				InputStream fileContent = filePart.getInputStream();
				File targetFile = new File ("WebContent/poster/"+fileName);
				FileUtils.copyInputStreamToFile(fileContent, targetFile);
				String imagePath = "poster/"+fileName;
				// END OF TESTING UPLOAD OF FILE.

			    //INSERT INTO TABLE MOVIES
				String insertQuery = "INSERT INTO movies VALUES (DEFAULT,'"
						+ movieTitle + "','" + imagePath + "','" + director
						+ "','" + sypnosis + "','" + ageRating + "','"+releaseDate+"')";
				
				stmtInsert.execute(insertQuery);
				
				//GET THE NEWEST MOVIEID
				ResultSet movieSet = stmtMovies.executeQuery("SELECT * FROM movies WHERE title='"+movieTitle+"'");
				int movieID = 0;
				while(movieSet.next()){
					 movieID = movieSet.getInt("movieid");
				}
				
				//INSERT INTO RESOLVEGENRE
				for(String genre : genres){
					int genreID = Integer.parseInt(genre);
					insertQuery = "INSERT INTO resolvegenre VALUES (" + movieID + "," + genreID + ")";
					stmtInsert.execute(insertQuery);
				}
				
				//INSERT INTO RESOLVEACTOR
				String[] actors = actor.split(",");
				for(String name : actors){
					name = name.toLowerCase();
					name = name.trim();
					name = name.replace("'", " ");
					
					ResultSet getActor = stmtActor.executeQuery("SELECT * FROM actor WHERE name='"+name+"'");
					int actorID = 0;
					
					if(!getActor.next()){
						stmtInsert.execute("INSERT INTO actor VALUES (DEFAULT,'"+name+"')");
		
						//GET THE NEWEST actorID
						ResultSet getActorID = stmtActor2.executeQuery("SELECT * FROM actor WHERE name='"+name+"'");
						
						while(getActorID.next()){
							 actorID = getActorID.getInt("actorid");
						}
					}
					else{
						actorID = getActor.getInt("actorid");
					}
					
					stmtInsert.execute("INSERT INTO resolveactor VALUES ("+movieID+","+actorID+")");
					
				}

			}
			//***************************************************************
			//						ADD CINEMA	  
			//***************************************************************
			else if (addCinema != null) {
				String cinemaName = request.getParameter("cinemaName");
				String location = request.getParameter("location");
				String capacity = request.getParameter("capacity");
				String[] amenities = request.getParameterValues("amenitiesid");

				 
				cinemaName = cinemaName.toLowerCase();
				location = location.toLowerCase();
				capacity = capacity.toLowerCase();

				cinemaName = cinemaName.replace("'"," ");
				location = location.replace("'"," ");
				capacity = capacity.replace("'"," ");
				
				int capacityInt;
				
				//VALIDATION
			    if(cinemaName.equals("") || location.equals("") || capacity.equals("")){
			    	request.getSession().setAttribute("message", "Please fill all fields");
					return false;
				}

				// CHECK IF ITS INTEGER
				try {
					capacityInt = Integer.parseInt(capacity);
					String insertQuery = "INSERT INTO cinema VALUES (DEFAULT,'"
							+ cinemaName + "','" + location + "',"
							+ capacityInt + ")";
					stmtInsert.execute(insertQuery);
				} catch (NumberFormatException e) {
					return false;
				}
				
				//GET THE NEWEST cinemaID
				ResultSet cinemaSet = stmtCinema.executeQuery("SELECT * FROM cinema WHERE name='"+cinemaName+"'");
				int cinemaID = 0;
				while(cinemaSet.next()){
					 cinemaID = cinemaSet.getInt("cinemaid");
				}
				
				//INSERT INTO RESOLVEAMENITIES
				for(String amenity : amenities){
					int amenitiesID = Integer.parseInt(amenity);
					String insertQuery = "INSERT INTO resolveamenities VALUES (" + cinemaID + "," + amenitiesID + ")";
					stmtInsert.execute(insertQuery);
				}
				
				
				
			}
			
			//***************************************************************
			//						ADD AMENITIES
			//***************************************************************
			else if (addAmenities != null) {
				String amenitiesName = request.getParameter("amenitiesName");
				amenitiesName = amenitiesName.toLowerCase();
				amenitiesName = amenitiesName.trim();
				amenitiesName = amenitiesName.replace("'", " ");
				
				//VALIDATION
			    if(amenitiesName.equals("")){
			    	request.getSession().setAttribute("message", "Please enter the amenities");
					return false;
				}
				String insertQuery = "INSERT INTO amenities VALUES (DEFAULT,'"
						+ amenitiesName + "')";
				stmtInsert.execute(insertQuery);
			}
			//***************************************************************
			//						ADD ACTOR	  
			//***************************************************************
			else if (addActor != null) {
				String actorName = request.getParameter("actorName");
				actorName = actorName.toLowerCase();
				actorName = actorName.trim();
				actorName = actorName.replace("'", " ");
				
				//VALIDATION
			    if(actorName.equals("")){
			    	request.getSession().setAttribute("message", "Please enter actor name");
					return false;
				}
				String insertQuery = "INSERT INTO actor VALUES (DEFAULT,'"
						+ actorName +"')";
				stmtInsert.execute(insertQuery);
			}
			//***************************************************************
			//						ADD GENRE
			//***************************************************************
			else if (addGenre != null) {
				String genreName = request.getParameter("genreName");
				genreName = genreName.toLowerCase();
				genreName = genreName.trim();
				genreName = genreName.replace("'", " ");
				
				//VALIDATION
			    if(genreName.equals("")){
			    	request.getSession().setAttribute("message", "Please enter genre type");
					return false;
				}
				String insertQuery = "INSERT INTO genre VALUES (DEFAULT,'"
						+ genreName + "')";
				stmtInsert.execute(insertQuery);
			}
			//***************************************************************
			//						ADD COMMENT
			//***************************************************************
			else if (addComment != null) {
				
				String userID = (String) request.getSession().getAttribute("userid");
				String movieID = request.getParameter("movieid");
				String rating = request.getParameter("rating");
				String comment = request.getParameter("comment");
				
				int intRating = Integer.parseInt(rating);
				
				int intUserID = Integer.parseInt(userID);
				int intMovieID = Integer.parseInt(movieID);
				
				comment = comment.toLowerCase();
				comment = comment.trim();
				comment = comment.replace("'", " ");
				
				//VALIDATION
			    if(rating.equals("")){
			    	request.getSession().setAttribute("message", "Please enter the comment");
					return false;
				}
				
				String insertQuery = "INSERT INTO comment VALUES (DEFAULT,"+intMovieID+","+intUserID+",'"+comment+"',"+intRating+".0)";
				stmtInsert.execute(insertQuery);
				
				
			}
			//***************************************************************
			//						ADD SHOWTIMES
			//***************************************************************
			else if (addShowTimes != null) {
		        String getCinemaQuery = "SELECT * FROM cinema";
		        ResultSet resultCinema= stmtCinema.executeQuery(getCinemaQuery);
		        while(resultCinema.next()){
		        	String cinemaID = resultCinema.getString("cinemaid");
		        	
		        	String[] showTimes = request.getParameterValues(cinemaID);
		        	if(showTimes == null){
		        		continue;
		        	}
		        	
		        	String movieID = request.getParameter("movieid");
		        	int resolveMoviesID = 0;
		        	

		        	//GET THE RESOLVEMOVIESID
		        	String getResolveMoviesIDQuery = "SELECT * FROM resolvemovies WHERE movieID="+movieID+" AND cinemaid ="+cinemaID;
			        ResultSet resultResolveMovies= stmtResolveMovies.executeQuery(getResolveMoviesIDQuery);
			        
			        if(resultResolveMovies.next()){ //IF EXISTS
			        	resolveMoviesID = resultResolveMovies.getInt("resolveMoviesID");
			        }
			        
			        if(resolveMoviesID == 0){
			        	//IF NOT, INSERT
			        	String insertQuery = "INSERT INTO resolvemovies VALUES (DEFAULT,"+cinemaID+","+movieID+")";
						stmtInsert.execute(insertQuery);
						//THEN GET THE NEWEST RESOLVEMOVIESID
						resultResolveMovies= stmtResolveMovies.executeQuery(getResolveMoviesIDQuery);
				        if(resultResolveMovies.next()){
				        	resolveMoviesID = resultResolveMovies.getInt("resolveMoviesID");
						}
			        }
			        
			        for(String time : showTimes){
			        	String insertQuery = "INSERT INTO showtimes VALUES (DEFAULT,"+resolveMoviesID+",'"+time+"')";
						stmtInsert.execute(insertQuery);
		        	}
			        
		        }
		    	
			}
			//***************************************************************
			//						ADD BOOKING
			//***************************************************************
			else if (addBooking != null) {
				String bookingDate = request.getParameter("bookingdate");
				String showTimeID = request.getParameter("showtimeid");
				String noOfTickets = request.getParameter("nooftickets");
				String userID = (String) request.getSession().getAttribute("userid");
				
				String insertQuery = "INSERT INTO booking VALUES (DEFAULT,"+userID+","+noOfTickets+","+showTimeID+",'"+bookingDate+"')";
				stmtInsert.execute(insertQuery);
				
	        }


			conn.close();
			stmtInsert.close();
			stmtActor.close();
			stmtActor2.close();
			stmtCinema.close();
			stmtMovies.close();
			stmtResolveMovies.close();
			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return true;
	}

	private static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	private Connection conn;
	private Statement stmtInsert;
	private Statement stmtActor;
	private Statement stmtActor2;
	private Statement stmtCinema;
	private Statement stmtMovies;
	private Statement stmtResolveMovies;
}
