package stuff;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String toString(){
		return this.username + " " + this.password;
	}
}
