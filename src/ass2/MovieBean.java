package ass2;



import java.util.*; 

import java.io.Serializable;

/*
 * This keeps a track of the person's chosen itinerary
 */
public class MovieBean implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieBean() {
		movieID = 0;
		title = "";
		poster = "";
		director = "";
		sypnosis = "";
		ageRating = "";
		releaseDate = new Date();
		genres = new ArrayList<String>();
		ratingString = "";
		ratingDouble = 0;
		actors = new ArrayList<String>();
	}
	public void setMovieID(int id){
		this.movieID = id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setPoster(String poster){
		this.poster = poster;
	}
	public void setDirector(String director){
		this.director = director;
	}
	public void setSypnosis(String sypnosis){
		this.sypnosis = sypnosis;
	}
	public void setAgeRating(String ageRating){
		this.ageRating = ageRating;
	}
	public void setReleaseDate(Date date){
		this.releaseDate = date;
	}
	public void setGenre(String genre){
		this.genres.add(genre);
	}
	public void setRatingString(String rating){
		this.ratingString = rating;
	}
	public void setRatingDouble(double rating){
		this.ratingDouble = rating;
	}
	public void setActor(String actor){
		this.actors.add(actor);
	}
	
	
	
	
	public int getMovieID(){
		return this.movieID;
	}
	public String getTitle(){
		return this.title;
	}
	public String getPoster() {
		return this.poster;
	}
	public String getDirector() {
		return this.director;
	}
	public String getSypnosis() {
		return this.sypnosis;
	}
	public String getAgeRating() {
		return this.ageRating;
	}
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	public ArrayList<String> getGenre() {
		return this.genres;
	}
	public String getRatingString() {
		return this.ratingString;
	}
	public double getRatingDouble() {
		return this.ratingDouble;
	}
	public ArrayList<String> getActor(){
		return this.actors;
	}
	

	private int movieID;
	private String title;
	private String poster;
	private String director;
	private String sypnosis;
	private String ageRating;
	private Date releaseDate;
	private ArrayList<String> genres;
	private String ratingString;
	private double ratingDouble;
	private ArrayList<String> actors;

	
	
}