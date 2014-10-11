package ass2;

import java.util.*;

import java.io.Serializable;

/*
 * This keeps a track of the person's chosen itinerary
 */
public class SearchResultBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchResultBean() {
		result = new ArrayList<MovieBean>();
	}

	public void setResult(MovieBean movie) {
		result.add(movie);
	}

	public ArrayList<MovieBean> getResult() {
		return result;
	}

	private ArrayList<MovieBean> result;

}