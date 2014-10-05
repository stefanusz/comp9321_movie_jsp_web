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
		//actors = "";
	}
	public void setBeanID(int id){
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
	public void setDate(Date date){
		this.releaseDate = date;
	}
	public void setGenre(String genre){
		this.genres.add(genre);
	}
	
	
	
	public int getBeanID(){
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
	
	

	private int movieID;
	private String title;
	private String poster;
	private String director;
	private String sypnosis;
	private String ageRating;
	private Date releaseDate;
	private ArrayList<String> genres;
	//private String actors;

	
	
}