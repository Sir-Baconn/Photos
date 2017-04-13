package stuff;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing the functionality to write data
 * @author Steve Matti, Gustavo Flores
 *
 */
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Directory for storage
	 */
	public static final String storeDir = "src/dat";
	/**
	 * Name of file for storage
	 */
	public static final String storeFile = "users.dat";
	/**
	 * First start up
	 */
	public boolean isEmpty = true;
	/**
	 * Users currently in the database
	 */
	public List<User> users;										//change this to a list
	/**
	 * The admin of the entire thing
	 */
	public Admin admin;
	
	public Account(){
		users = new ArrayList<User>();
	}
	
	/**
	 * Adds a user to the list
	 * @param username Username of the new User
	 * @param password Password of the new User
	 * @throws IOException
	 */
	public void addUser(String username, String password) throws IOException{
		users.add(new User(username, password));
		this.isEmpty = false;
	}
	
	/**
	 * Writes the data to the .dat file
	 * @param account Data class to be written
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeAccount(Account account) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(account);
	}
	
	/**
	 * Reads from the .dat file
	 * @return Object containing all the data
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Account readAccount() throws IOException, ClassNotFoundException{
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
			return (Account)ois.readObject();
		}catch(EOFException e){
			System.out.println("No account data on record, starting new data file");
			return new Account();
		}
	}
	
	public static Account getAccount(){
		try {
			Account acc = readAccount();
			return acc;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("we reached here");
		return null;
	}
	
	public boolean userExists(String username, String password){
		User tempUser = new User(username, password);
		for(User user : users){
			if(user.equals(tempUser)){
				System.out.println(tempUser);
				return true;
			}
		}
		System.out.println("User does not exist");
		return false;
	}
	
	public void printUsers(){
		for(User user : users){
			System.out.println("User: " + user);
			for(Album album : user.albums){
				System.out.println("Album: " + album);
			}
		}
	}
	
	public boolean adminExists(){
		return admin != null ? true : false;
	}

	public List<User> getAllUsers() {
		return this.users;
	}
	
	public List<Album> getAllUserAlbums(User user){
		return user.albums;
	}
	
	/**
	 * Gets the designated User
	 * @param username User's username
	 * @param password User's password
	 * @return
	 */
	public User getUser(String username, String password){
		User tempUser = new User(username, password);
		for(User user : users){
			if(user.equals(tempUser)){
				return user;
			}
		}
		System.out.println("User was not found");
		return null;
	}
	
	/**
	 * Deletes a given User
	 * @param userToDelete
	 * @return
	 */
	public boolean deleteUser(User userToDelete){
		//loop is unnecessary
		for(User user : users){
			if(user.equals(userToDelete)){
				users.remove(userToDelete);
				return true;
			}
		}
		//System.out.println("User to delete was not found");
		return false;
	}
	
	public void addAlbum(User currentUser, Album newAlbum){
		currentUser.albums.add(newAlbum);
	}
	
	public boolean deleteAlbum(User userHoldingAlbum, Album albumToDelete){
		//these loops are unnecessary checks really
		for(User user : users){
			if(user.equals(userHoldingAlbum)){
				for(Album album : user.albums){
					if(album.equals(albumToDelete)){
						//remove album
						user.albums.remove(albumToDelete);
						return true;
					}
				}
				//System.out.println("Album was not found");
			}
		}
		//System.out.println("User was not found");
		return false;
	}
	
	public static void clearAccountData(){
		try {
			new FileOutputStream(storeDir + File.separator + storeFile).close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
