package stuff;

import java.io.IOException;

public class Admin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Admin(String username, String password) {
		super(username, password);
	}
	
	public static boolean isAdmin(String givenUsername, String givenPassword){
		return givenUsername.equals("admin") && givenPassword.equals("admin");
	}
	
	public boolean createUser(String username, String password){
		if(!PhotoAlbum.globalAccount.userExists(username, password)){
			try {
				System.out.println("Admin: added a new user");
				PhotoAlbum.globalAccount.addUser(username, password);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Admin: failed to add new user because user already exists");
		return false;
	}
	
}
