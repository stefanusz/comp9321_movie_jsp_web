package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();
			
			ResultSet allMovies = stmt.executeQuery("SELECT * FROM movies");
			if(allMovies.next()){
				return false;
			}
			
			
			//GET ALL PARAMETER
			String username = request.getParameter("username");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			
			//CONVERT TO LOWER CASE
			username = username.toLowerCase();
		    firstName = firstName.toLowerCase();
		    lastName = lastName.toLowerCase();
		    nickname = nickname.toLowerCase();
		    email = email.toLowerCase();
		    
		    //VALIDATION
		    if(username.equals("") || firstName.equals("") || nickname.equals("") || email.equals("")){
				return false;
			}
			if(!password.equals(password2)){
				return false;
			}
			ResultSet result = stmt.executeQuery("SELECT USERNAME FROM users WHERE USERNAME = '"+username+"'");
			if(result.next()){
				return false;
			}
			result = stmt.executeQuery("SELECT EMAIL FROM users WHERE EMAIL = '"+email+"'");
			if(result.next()){
				return false;
			}
			
			String hashedPassword = hashing(password);
			
			//INSERT INTO DATABASE
			stmt.execute("INSERT INTO users VALUES (DEFAULT,'"+username+"','"+firstName+"','"+lastName+"','"+nickname+"','"+email+"','"+hashedPassword+"','user')");
		
			
			conn.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	

	
	private Connection conn;
	private Statement stmt;
}
