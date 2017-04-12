package stuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album implements Serializable{
	
	//needs stuff - list of photos, the user of these photos, etc (look at assignment)
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	public List<Photo> photos;
	private int numPhotos;
	
	public Album(String name){
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getNumPhotos(){
		return photos.size();
	}
	
	public Date getEarliestDatedPhoto(){
		return new Date();
	}
	
	public Date getLatestDatedPhoto(){
		return new Date();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
	
	public boolean equals(Object o){
		if(o == null || !(o instanceof Album)){
			return false;
		}
		Album other = (Album)o;
		
		return this.name.equals(other.name);
	}
}
