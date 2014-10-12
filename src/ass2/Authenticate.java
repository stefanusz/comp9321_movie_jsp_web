package ass2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authenticate implements Command {

	private Connection conn;
	private Statement stmt;
	private Statement stmt2;

	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String login = request.getParameter("login");
		String logout = request.getParameter("logout");
		String activation = request.getParameter("activation");

		// *************************************************
		// 						LOGIN
		// *************************************************
		if (login != null) {
			try {
				conn = DBConnectionFactory.getConnection();
				stmt = conn.createStatement();

				String username = request.getParameter("username");
				String password = request.getParameter("password");

				username = username.toLowerCase();
				String hashedPassword = hashing(password);

				String sqlQuery = "SELECT * FROM users WHERE username = '"
						+ username + "'";

				ResultSet result = stmt.executeQuery(sqlQuery);

				while (result.next()) {

					String dbUserID = result.getString("userid");
					String dbUsername = result.getString("username");
					String dbPassword = result.getString("password");
					String dbFirstName = result.getString("first_name");
					String dbLastName = result.getString("last_name");
					String dbRole = result.getString("role");
					String dbNickName = result.getString("nickname");
					String dbEmail = result.getString("email");
					String dbStatus = result.getString("status");

					if (dbUsername.equalsIgnoreCase(username)
							&& dbStatus.equalsIgnoreCase("active")) {

						if (hashedPassword.equals(dbPassword)) {
							request.getSession().setAttribute("userid",
									dbUserID);
							request.getSession().setAttribute("username",
									dbUsername);
							request.getSession().setAttribute("first_name",
									dbFirstName);
							request.getSession().setAttribute("last_name",
									dbLastName);
							request.getSession().setAttribute("role", dbRole);
							request.getSession().setAttribute("nickname",
									dbNickName);
							request.getSession().setAttribute("email", dbEmail);
							return true;
						}

					} else {
						return false;
					}

				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		} 
		// *************************************************
		// 						LOGOUT
		// *************************************************
		else if (logout != null) {

			HttpSession session = request.getSession(false);
			session.invalidate();
			return true;
		} 
		
		// *************************************************
		// 				ACTIVATION CHECKING
		// *************************************************
		
		else if (activation != null) {

			String username = request.getParameter("username");

			try {
				conn = DBConnectionFactory.getConnection();
				stmt = conn.createStatement();

				String sqlQuery = "SELECT status FROM users WHERE username = '"
						+ username + "'";

				ResultSet result = stmt.executeQuery(sqlQuery);

				while (result.next()) {
					String status = result.getString("status");

					if (status.equals(activation)) {

						String updateStatus = "UPDATE users SET status='active' WHERE username='"
								+ username + "'";
						stmt2 = conn.createStatement();
						stmt2.executeUpdate(updateStatus);
						return true;
					} else {
						return false;
					}
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return false;

	}
	// *************************************************
	// 					HASHING OF TEXT METHOD. 
	// *************************************************
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
