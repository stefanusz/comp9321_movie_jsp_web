package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Command {

	private Connection conn;
	private Statement stmt;

	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmt = conn.createStatement();

			System.out.println("COMES IN333");

				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				String hashedPassword = hashing(password);
				
				String sqlQuery = "SELECT * FROM users WHERE email = '" +email+"'";
				
				ResultSet result = stmt.executeQuery(sqlQuery);
				
				while (result.next()){
					
					String dbEmail = result.getString("email");
					String dbUsername = result.getString("username");
					String dbPassword = result.getString("password");
					String dbFirstName = result.getString("first_name");
					String dbLastName = result.getString("last_name");
					String dbRole = result.getString("role");
					String dbNickName = result.getString("nickname");
					
					System.out.println(dbUsername);
					System.out.println(dbPassword);
					System.out.println(dbFirstName);

					
					if(dbEmail.equalsIgnoreCase(email)){
						
						if(hashedPassword.equals(dbPassword)){
							System.out.println("COMES In");
							request.getSession().setAttribute("username", dbUsername);
							request.getSession().setAttribute("first_name", dbFirstName);
							request.getSession().setAttribute("last_name", dbLastName);
							request.getSession().setAttribute("role", dbRole);
							request.getSession().setAttribute("nickname", dbNickName);
							request.getSession().setAttribute("email", dbEmail);
							return true;
						}
						
					}
					
				}
			

			
			

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;

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

}
