package ass2;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.derby.client.am.Decimal;

@MultipartConfig
public class AddCommand implements Command {

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();

			String addMovies =  request.getParameter("addMovies");
			String addCinema = request.getParameter("addCinema");
			String addAmenities = request.getParameter("addAmenities");
			String addActor =  request.getParameter("addActor");
			String addGenre=  request.getParameter("addGenre");
			String addComment =  request.getParameter("addComment");
			

			if (addMovies != null) {

				// TESTING UPLOAD OF FILE.
				Part filePart = request.getPart("poster");
				String fileName = getFileName(filePart);
				InputStream fileContent = filePart.getInputStream();
				File targetFile = new File ("poster/"+fileName);
				FileUtils.copyInputStreamToFile(fileContent, targetFile);
				String imagePath = "poster/"+fileName;
				//System.out.println(System.getProperty("user.dir"));
				// END OF TESTING UPLOAD OF FILE.

				// START OF GETTING THE DIFFERENT VARIABLE FOR THE NORMAL FORM. 
				String movieTitle = request.getParameter("movieTitle");
				String[] genres = request.getParameterValues("genreid");
				String director = request.getParameter("director");
				String sypnosis = request.getParameter("sypnosis");
				String ageRating = request.getParameter("ageRating");
				String release_day= request.getParameter("release_day");
				String release_month= request.getParameter("release_month");
				String release_year= request.getParameter("release_year");
				// END OF GETTING THE DIFFERENT VARIABLE FOR THE FORM.
				
				movieTitle = movieTitle.toLowerCase();
				director = director.toLowerCase();
				sypnosis = sypnosis.toLowerCase();
				ageRating = ageRating.toLowerCase();
				release_day = release_day.toLowerCase();
				release_month = release_month.toLowerCase();
				release_year = release_year.toLowerCase();
				
				//VALIDATION
			    if(movieTitle.equals("") || director.equals("") || sypnosis.equals("") || ageRating.equals("")||release_day.equals("")||release_month.equals("")||release_year.equals("")){
					return false;
				}
			    if(release_month == "4"||release_month == "6"||release_month == "9"||release_month == "11"){
			    	if(Integer.parseInt(release_day) > 30){
			    		return false;
			    	}
			    }
			    if(release_month == "2"){
			    	if(Integer.parseInt(release_day) > 28){
			    		return false;
			    	}
			    }
				
			    //INSERT INTO TABLE MOVIES
			    String releaseDate = release_year + "-" + release_month + "-" + release_day;
				String insertQuery = "INSERT INTO movies VALUES (DEFAULT,'"
						+ movieTitle + "','" + imagePath + "','" + director
						+ "','" + sypnosis + "','" + ageRating + "','"+releaseDate+"')";
				
				stmt.execute(insertQuery);
				
				//GET THE NEWEST MOVIEID
				ResultSet movieSet = stmt.executeQuery("SELECT * FROM movies WHERE title='"+movieTitle+"'");
				int movieID = 0;
				while(movieSet.next()){
					 movieID = movieSet.getInt("movieid");
				}
				
				//INSERT INTO RESOLVEGENRE
				for(String genre : genres){
					int genreID = Integer.parseInt(genre);
					insertQuery = "INSERT INTO resolvegenre VALUES (" + movieID + "," + genreID + ")";
					stmt.execute(insertQuery);
				}
				

			}

			else if (addCinema != null) {
				String cinemaName = request.getParameter("cinemaName");
				String location = request.getParameter("location");
				String capacity = request.getParameter("capacity");
				String[] amenities = request.getParameterValues("amenitiesid");

				 
				cinemaName = cinemaName.toLowerCase();
				location = location.toLowerCase();
				capacity = capacity.toLowerCase();
				
				int capacityInt;
				
				//VALIDATION
			    if(cinemaName.equals("") || location.equals("") || capacity.equals("")){
					return false;
				}

				// CHECK IF ITS INTEGER
				try {
					capacityInt = Integer.parseInt(capacity);
					String insertQuery = "INSERT INTO cinema VALUES (DEFAULT,'"
							+ cinemaName + "','" + location + "',"
							+ capacityInt + ")";
					stmt.execute(insertQuery);
				} catch (NumberFormatException e) {
					return false;
				}
				
				//GET THE NEWEST cinemaID
				ResultSet cinemaSet = stmt.executeQuery("SELECT * FROM cinema WHERE name='"+cinemaName+"'");
				int cinemaID = 0;
				while(cinemaSet.next()){
					 cinemaID = cinemaSet.getInt("cinemaid");
				}
				
				//INSERT INTO RESOLVEAMENITIES
				for(String amenity : amenities){
					int amenitiesID = Integer.parseInt(amenity);
					String insertQuery = "INSERT INTO resolveamenities VALUES (" + cinemaID + "," + amenitiesID + ")";
					stmt.execute(insertQuery);
				}
				
			}
			else if (addAmenities != null) {
				String amenitiesName = request.getParameter("amenitiesName");
				amenitiesName = amenitiesName.toLowerCase();
				
				//VALIDATION
			    if(amenitiesName.equals("")){
					return false;
				}
				String insertQuery = "INSERT INTO amenities VALUES (DEFAULT,'"
						+ amenitiesName + "')";
				stmt.execute(insertQuery);
			}
			else if (addActor != null) {
				String actorName = request.getParameter("actorName");
				String gender = request.getParameter("gender");
				String dob_day= request.getParameter("dob_day");
				String dob_month= request.getParameter("dob_month");
				String dob_year= request.getParameter("dob_year");
				
				actorName = actorName.toLowerCase();
				gender = gender.toLowerCase();
				dob_day = dob_day.toLowerCase();
				dob_month = dob_month.toLowerCase();
				dob_year = dob_year.toLowerCase();
				
				//VALIDATION
			    if(actorName.equals("") || gender.equals("") || dob_day.equals("") || dob_month.equals("")||dob_year.equals("")){
					return false;
				}
			    if(dob_month == "4"||dob_month == "6"||dob_month == "9"||dob_month == "11"){
			    	if(Integer.parseInt(dob_day) > 30){
			    		return false;
			    	}
			    }
			    if(dob_month == "2"){
			    	if(Integer.parseInt(dob_day) > 28){
			    		return false;
			    	}
			    }
				
				String dob = dob_year + "-" + dob_month + "-" + dob_day;
				String insertQuery = "INSERT INTO actor VALUES (DEFAULT,'"
						+ actorName + "','" + gender + "','"+dob+"')";
				stmt.execute(insertQuery);
			}
			else if (addGenre != null) {
				String genreName = request.getParameter("genreName");
				genreName = genreName.toLowerCase();
				
				//VALIDATION
			    if(genreName.equals("")){
					return false;
				}
				String insertQuery = "INSERT INTO genre VALUES (DEFAULT,'"
						+ genreName + "')";
				stmt.execute(insertQuery);
			}
			else if (addComment != null) {
				
				String userID = (String) request.getSession().getAttribute("userid");
				String movieID = request.getParameter("movieid");
				String rating = request.getParameter("rating");
				String comment = request.getParameter("comment");
				
				int intRating = Integer.parseInt(rating);
				//BigDecimal decimalRating = new BigDecimal(rating);
				//DecimalFormat decimalRating = new DecimalFormat(rating);
				
				int intUserID = Integer.parseInt(userID);
				int intMovieID = Integer.parseInt(movieID);
				
				comment = comment.toLowerCase();
				
				System.out.println(intRating +" "+intUserID+" "+intMovieID+" "+comment);
				
				//VALIDATION
			    if(rating.equals("")){
					return false;
				}
				
				String insertQuery = "INSERT INTO comment VALUES (DEFAULT,"+intMovieID+","+intUserID+",'"+comment+"',"+rating+".0)";
				System.out.println(insertQuery);
				stmt.execute(insertQuery);
				
				
			}


			conn.close();
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
	private Statement stmt;
}
