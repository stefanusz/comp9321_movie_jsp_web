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

			String login = request.getParameter("login");
			if (login != null) {

				String username = request.getParameter("username");
				String password = (String) request.getParameter("password");
				
				String hashedPassword = hashing(password);
				
				String sqlQuery = "SELECT username, password, role FROM movie WHERE username = '" +username+"'";
				
				ResultSet result = stmt.executeQuery(sqlQuery);
				
				while (result.next()){
					
				}
			}

			
			

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

}
