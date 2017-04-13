package stuff;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Photo class
 * @author Steven Mattia
 *
 */
public class Photo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//needs stuff - dates, times, tags, etc (look at assignment)
	
	private String name;
	/**
	 * Path photo is contained in
	 */
	private String filePath;
	/**
	 * Photo date taken
	 */
	private Date dateUploaded;
	/**
	 * Photo's caption
	 */
	private String caption;
	/**
	 * Hashmap of tags
	 */
	private HashMap<String, String> tags;
	
	public Photo(String path){
		this.filePath = path;
		tags = new HashMap<String, String>();
	}
	
	public Photo(String path, String name){
		this.filePath = path;
		this.name = name;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setPath(String s){
		this.filePath = s;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	public void setDateUploaded(Date d){
		this.dateUploaded = d;
	}
	
	public Date getDateUploaded(){
		return this.dateUploaded;
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	
	public String getCaption(){
		return this.caption;
	}
	
	public HashMap<String, String> getTags(){
		return this.tags;
	}
	
	
	public void addTags(String key, String val){
		tags.put(key, val);
	}
	
	public String toString(){
		return this.filePath;
	}

}
