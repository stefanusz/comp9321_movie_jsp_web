package ass2;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.taglibs.standard.extra.spath.Path;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@MultipartConfig
public class AddCommand implements Command {

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

				System.out.println(System.getProperty("user.dir"));
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
				String insertQuery = "INSERT INTO amenities VALUES (DEFAULT,'"
						+ amenitiesName + "')";
				stmt.execute(insertQuery);
			} else if (addActor != null) {
				String actorName = request.getParameter("actorName");
				String gender = request.getParameter("gender");
				String dob = request.getParameter("dob");
				String insertQuery = "INSERT INTO actor VALUES (DEFAULT,'"
						+ actorName + "','" + gender + "','" + dob + ")";
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
