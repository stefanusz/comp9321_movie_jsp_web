package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ServiceLocatorException;


public class RegisterCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();
			
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
		    if(username=="" || firstName=="" || nickname=="" || email==""){
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
