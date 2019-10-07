package com.deetechs.wordwiz;

public class Contact {	
	//private variables
	int id;
	String count;
	String level;
	String wrong_tracker;
	 //Empty constructor
	 public Contact(){
	    	
	    }	
  //constructor
    public Contact(String count,String level,String wrong_tracker){       
        this.count = count;
        this.level = level;
        this.wrong_tracker = wrong_tracker;
    }   
  //constructor
    public Contact(int id,String count,String level,String wrong_tracker){
        this.id = id;
        this.count = count;
        this.level = level;
        this.wrong_tracker = wrong_tracker;
    }
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getWrong_tracker() {
		return wrong_tracker;
	}
	public void setWrong_tracker(String wrongTracker) {
		wrong_tracker = wrongTracker;
	}
	
}
