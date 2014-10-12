package ass2;

import java.util.*; 
import java.io.Serializable;

public class BookingBean implements Serializable { 
	private static final long serialVersionUID = 1L;

	public BookingBean() {
		cinemaName = "";
		movieTitle = "";
		bookingDate = "";
		time = "";
		noOfTicket = 0;
	}
	public void setCinemaName(String name){
		this.cinemaName = name;
	}
	public void setMovieTitle(String title){
		this.movieTitle = title;
	}
	public void setBookingDate(String date){
		this.bookingDate = date;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setNoOfTicket(int ticket){
		this.noOfTicket = ticket;
	}
	
	
	public String getCinemaName(){
		return this.cinemaName;
	}
	public String getMovieTitle(){
		return this.movieTitle;
	}
	public String getBookingDate(){
		return this.bookingDate;
	}
	public String getTime(){
		return this.time;
	}
	public int getNoOfTicket(){
		return this.noOfTicket;
	}

	private String cinemaName;
	private String movieTitle;
	private String bookingDate;
	private String time;
	private int noOfTicket;
	
	
}