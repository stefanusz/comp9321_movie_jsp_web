package ass2;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

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
				String[] genres = request.getParameterValues("genre");
				String director = request.getParameter("director");
				String sypnosis = request.getParameter("sypnosis");
				String ageRating = request.getParameter("ageRating");
				String actor = request.getParameter("actor");
				// END OF GETTING THE DIFFERENT VARIABLE FOR THE FORM.

				String insertQuery = "INSERT INTO movies VALUES (DEFAULT,'"
						+ movieTitle + "','" + imagePath + "','" + director
						+ "','" + sypnosis + "','" + ageRating + "')";
				 stmt.execute(insertQuery);

			}

			else if (addCinema != null) {
				String cinemaName = request.getParameter("cinemaName");
				String location = request.getParameter("location");
				String capacity = request.getParameter("capacity");
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
			} else if (addAmenities != null) {
				String amenitiesName = request.getParameter("amenitiesName");
				
				//VALIDATION
			    if(amenitiesName.equals("")){
					return false;
				}
				String insertQuery = "INSERT INTO amenities VALUES (DEFAULT,'"
						+ amenitiesName + "')";
				stmt.execute(insertQuery);
			} else if (addActor != null) {
				String actorName = request.getParameter("actorName");
				String gender = request.getParameter("gender");
				String dob_day= request.getParameter("dob_day");
				String dob_month= request.getParameter("dob_month");
				String dob_year= request.getParameter("dob_year");
				
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
