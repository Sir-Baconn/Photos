package stuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public List<Album> albums;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
		albums = new ArrayList<Album>();
	}
	
	public void addAlbum(Album album){
		albums.add(album);
	}
	
	public String toString(){
		return this.username + " " + this.password;
	}
	
	public boolean equals(Object o){
		if(o == null || !(o instanceof User)){
			return false;
		}
		User other = (User)o;
		
		return this.username.equals(other.username) && this.password.equals(other.password);
	}
}
