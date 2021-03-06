package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();
			
			//GET ALL PARAMETER
			String username = request.getParameter("username");
			String firstName = (String) request.getParameter("firstName");
			String lastName = (String) request.getParameter("lastName");
			String nickname = (String) request.getParameter("nickname");
			String email = (String) request.getParameter("email");
			String oldPassword = (String) request.getParameter("oldPassword");
			String newPassword = (String) request.getParameter("newPassword");
			String newPassword2 = (String) request.getParameter("newPassword2");
			String dbPassword = "";
			
			//CONVERT TO LOWER CASE
			username = username.toLowerCase();
		    firstName = firstName.toLowerCase();
		    lastName = lastName.toLowerCase();
		    nickname = nickname.toLowerCase();
		    email = email.toLowerCase();
		    
		    //GET OLD VALUES FROM DATABASE
		    String sqlQuery = "SELECT * FROM users WHERE username = '" +username+"'";
			ResultSet oldData = stmt.executeQuery(sqlQuery);
			while (oldData.next()){
				dbPassword = oldData.getString("password");
			}
		    
		    
		    //VALIDATION
		    if(firstName.equals("") || nickname.equals("") || email.equals("")){
		    	request.getSession().setAttribute("message", "All fields must be filled");
				return false;
			}
		    sqlQuery = "SELECT * FROM users WHERE email = '" +email+"'";
			ResultSet checkEmail = stmt.executeQuery(sqlQuery);
			
		    if (checkEmail.next()) {
		    	String dbUser = checkEmail.getString("username");
		    	if(!dbUser.equals(username)){
		    		request.getSession().setAttribute("message", "Email already exists");
			    	return false;
		    	}
		    }
		    
		    
		    //UPDATE PASSWORD
		    if(!oldPassword.equals("") || !newPassword.equals("") || !newPassword2.equals("")){ //if any of them is not empty
	    		if(dbPassword.equals(hashing(oldPassword))){
		    		if(newPassword.equals(newPassword2)){
		    			String updateQuery = "UPDATE users SET password='"+hashing(newPassword)+"' WHERE username='"+username+"'"; 
		    			stmt.executeUpdate(updateQuery);
		    		}
		    		else{
		    			request.getSession().setAttribute("message", "Password and Re-type password not the same");
		    			return false;
		    		}
		    	}
		    	else{
		    		request.getSession().setAttribute("message", "Old password is wrong");
		    		return false;
		    	}
		    }
			
		    
		    //UPDATE OTHER DATA
			String updateQuery = "UPDATE users SET first_name='"+firstName+"',last_name='"+lastName+"',nickname='"+nickname+"',email='"+email+"' WHERE username='"+username+"'"; 
			stmt.execute(updateQuery);
			
			//UPDATE THE SESSION ATTRIBUTE
			request.getSession().setAttribute("first_name", firstName);
			request.getSession().setAttribute("last_name", lastName);
			request.getSession().setAttribute("nickname", nickname);
			request.getSession().setAttribute("email", email);
			
			
			conn.close();
			stmt.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	public String hashing(String password) {
		String hashedPassword = null;
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			hashedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hashedPassword;
	}
	
	private Connection conn;
	private Statement stmt;
}
