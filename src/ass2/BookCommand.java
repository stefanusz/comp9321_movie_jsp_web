package ass2;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			conn = DBConnectionFactory.getConnection();
			stmtCapacity = conn.createStatement();
			stmtBoughtTickets = conn.createStatement();
			stmtShowTime = conn.createStatement();


			String doBooking = request.getParameter("doBooking");
			
			
			// *************************************************
			// 				PROCESS OF BOOKING
			// *************************************************
			
			if(doBooking != null){
				String movieID = request.getParameter("movieid");
				String movieTitle = request.getParameter("movietitle");
				String cinemaID = request.getParameter("cinemaid");
				String cinemaName = request.getParameter("cinemaname");
				String bookingDate = request.getParameter("bookingdate");
				String time = request.getParameter("time");
				
				int capacity = 0;
				int boughtTickets = 0;
				int showTimeID = 0;
				
				String getCapacityQuery ="SELECT * FROM cinema WHERE cinemaid="+cinemaID;
				ResultSet resultCapacity = stmtCapacity.executeQuery(getCapacityQuery);
				if(resultCapacity.next()){
					 capacity = resultCapacity.getInt("capacity");
				}
				
				
				String getShowTimeIDQuery = "SELECT * FROM showtimes s JOIN resolvemovies rm ON s.resolvemoviesid = rm.resolvemoviesid WHERE rm.cinemaid="
						+ cinemaID
						+ " AND rm.movieid = "
						+ movieID
						+ " AND s.time ='" + time + "'";
				
				ResultSet resultShowTime = stmtShowTime.executeQuery(getShowTimeIDQuery);
				
				if(resultShowTime.next()){
					showTimeID = resultShowTime.getInt("showtimeid");
				}
				
				String getBoughtTicketsQuery ="SELECT SUM(noofticket) AS totalbought FROM booking WHERE showtimeid="+showTimeID;
				ResultSet resultBoughtTickets = stmtBoughtTickets.executeQuery(getBoughtTicketsQuery);
				if(resultBoughtTickets.next()){
					 boughtTickets = resultBoughtTickets.getInt("totalbought");
				}
				
				
				
				int availableTickets = capacity - boughtTickets;
				if(availableTickets == 0){
					return false;
				}
				
				request.setAttribute("movieID", movieID);
				request.setAttribute("movieTitle", movieTitle);
				request.setAttribute("cinemaID", cinemaID);
				request.setAttribute("cinemaName", cinemaName);
				request.setAttribute("bookingDate", bookingDate);
				request.setAttribute("time", time);
				request.setAttribute("availableTickets", availableTickets);
				request.setAttribute("showTimeID", showTimeID);
				
				
			}
			
			
			conn.close();
			stmtCapacity.close();
			stmtBoughtTickets.close();
			stmtShowTime.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	

	
	private Connection conn;
	private Statement stmtCapacity;
	private Statement stmtBoughtTickets;
	private Statement stmtShowTime;
}
