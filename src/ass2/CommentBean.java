package ass2;

import java.io.Serializable;

/*
 * This keeps a track of the person's chosen itinerary
 */
public class CommentBean implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CommentBean() {
		user = "";
		comment = "";
		rating = 0;

	}
	
	public void setUser(String user){
		this.user = user;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public void setRating(double rating){
		this.rating = rating;
	}
	

	public String getUser(){
		return this.user;
	}
	public String getComment(){
		return this.comment;
	}
	public double getRating(){
		return this.rating;
	}

	private String user;
	private String comment;
	private double rating;

	
	
}