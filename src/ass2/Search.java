package ass2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search implements Command {

	private Connection conn;
	private Statement stmt;

	@Override
	public boolean execute(HttpServletRequest request,
			HttpServletResponse response) {

		String search = request.getParameter("search");

		if (search != null) {

			

			try {
				conn = DBConnectionFactory.getConnection();
				stmt = conn.createStatement();

				
				String sqlQuery = "SELECT DISTINCT m.title, g.name FROM (SELECT * from movies) as m, "
						+ "(SELECT * FROM genre) as g, (SELECT * FROM resolveGenre) as rg "
						+ "WHERE m.title = 'a' OR g.name = 'comedy'";
				
				ResultSet result = stmt.executeQuery(sqlQuery);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
