package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
			stmt.execute("INSERT INTO users VALUES (DEFAULT,'"+username+"','"+firstName+"','"+lastName+"','"+nickname+"','"+email+"','"+hashedPassword+"','user','"+hashedPassword+"')");
		
			
			//SENDING OF EMAIL STARTS HERE.
			MailSender sender = null;
			sender = MailSender.getMailSender();
			String fromAddress = "shus363@cse.unsw.edu.au";
			String toAddress = email;
			String subject = "Mail activation for movie website";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Please click this link to activate your account: http://localhost:8080/MovieWebsite/control?username="+username+"&activation="+hashedPassword);
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
			//SENDING OF EMAIL ENDS HERE.			
			
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
