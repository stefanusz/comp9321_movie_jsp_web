package ass2;

import java.util.*; 
import java.io.Serializable;

public class CinemaBean implements Serializable { 
	private static final long serialVersionUID = 1L;

	public CinemaBean() {
		cinemaID = 0;
		name = "";
		showTimes = new ArrayList<String>();
		
		showTimes.add("10");
		showTimes.add("13");
		showTimes.add("16");
		showTimes.add("19");
		showTimes.add("22");
		showTimes.add("1");
	}
	public void setCinemaID(int id){
		this.cinemaID = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setShowTimes(String time){
		this.showTimes.add(time);
	}
	
	public int getCinemaID(){
		return this.cinemaID;
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<String> getShowTimes(){
		return this.showTimes;
	}
	
	
	public void removeShowTimes(String time){
		showTimes.remove(time);
	}


	private int cinemaID;
	private String name;
	private ArrayList<String> showTimes;
	
	
}