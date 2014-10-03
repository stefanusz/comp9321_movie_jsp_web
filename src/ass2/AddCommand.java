package ass2;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class AddCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();

			String addMovies= (String) request.getParameter("addMovies");
			String addCinema= (String) request.getParameter("addCinema");
			String addAmenities= (String) request.getParameter("addAmenities");
			String addActor= (String) request.getParameter("addActor");
			//System.out.println("AAAAAAAAAAAAAA");
			if(addMovies != null){

				String movieTitle = request.getParameter("movieTitle");
				String poster = request.getParameter("poster");

				String[] genres = request.getParameterValues("genre");
				String director = request.getParameter("director");
				String sypnosis = request.getParameter("sypnosis");
				String ageRating = request.getParameter("ageRating");

				String actor = request.getParameter("actor");
				//TO DO

				String insertQuery = "INSERT INTO movies VALUES (DEFAULT,'"+movieTitle+"','"+poster+"','"+director+"','"+sypnosis+"','"+ageRating+"')";
				//stmt.execute(insertQuery);


				
				File file ;
				int maxFileSize = 5000 * 1024;
				int maxMemSize = 5000 * 1024;
				ServletContext context = request.getServletContext();
				String filePath = context.getInitParameter("poster-upload");
				System.out.println("AAAAAAAAAAAAAA");
				
				// Verify the content type
				String contentType = request.getContentType();
				if ((contentType.indexOf("multipart/form-data") >= 0)) {

					DiskFileItemFactory factory = new DiskFileItemFactory();
					// maximum size that will be stored in memory
					factory.setSizeThreshold(maxMemSize);
					// Location to save data that is larger than maxMemSize.
					factory.setRepository(new File("temp"));

					// Create a new file upload handler
					ServletFileUpload upload = new ServletFileUpload(factory);
					// maximum file size to be uploaded.
					upload.setSizeMax( maxFileSize );
					try{ 
						// Parse the request to get file items.
						List fileItems = upload.parseRequest((RequestContext) request);

						// Process the uploaded file items
						Iterator i = fileItems.iterator();
						
						while ( i.hasNext () ) 
						{
							FileItem fi = (FileItem)i.next();
							
							if ( !fi.isFormField () )	
							{
								
								// Get the uploaded file parameters
								String fieldName = fi.getFieldName();
								String fileName = fi.getName();
								boolean isInMemory = fi.isInMemory();
								long sizeInBytes = fi.getSize();
								// Write the file
								if( fileName.lastIndexOf("\\") >= 0 ){
									file = new File( filePath + 
											fileName.substring( fileName.lastIndexOf("\\"))) ;
								}else{
									file = new File( filePath + 
											fileName.substring(fileName.lastIndexOf("\\")+1)) ;
								}
								fi.write( file ) ;

							}
						}

					}catch(Exception ex) {
						System.out.println(ex);
					}


					
					
					
					
					
					
				}	   
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
			else if(addAmenities != null){
				String amenitiesName = request.getParameter("amenitiesName");
				String insertQuery = "INSERT INTO amenities VALUES (DEFAULT,'"+amenitiesName+"')";
				stmt.execute(insertQuery);
			}
			else if(addActor != null){
				String actorName = request.getParameter("actorName");
				String gender = request.getParameter("gender");
				String dob = request.getParameter("dob");
				String insertQuery = "INSERT INTO actor VALUES (DEFAULT,'"+actorName+"','"+gender+"','"+dob+")";
				stmt.execute(insertQuery);
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
