package stuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Album class
 * @author Steven Mattia, Gustavo Flores
 *
 */
public class Album implements Serializable{
	
	//needs stuff - list of photos, the user of these photos, etc (look at assignment)
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name of the Album
	 */
	private String name;
	/**
	 * List of photos in the album
	 */
	private List<Photo> photos;
	/**
	 * Start date
	 */
	private Date startDate;
	/**
	 * End date
	 */
	private Date endDate;
	
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
	
	public List<Photo> getPhotos(){
		return this.photos;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addPhotoToAlbum(Photo photo){
		photos.add(photo);
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
