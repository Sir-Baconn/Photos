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

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String storeDir = "src/dat";
	public static final String storeFile = "users.dat";
	public boolean isEmpty = true;
	public List<User> users;										//change this to a list
	public Admin admin;
	
	public Account(){
		users = new ArrayList<User>();
	}
	
	public void addUser(String username, String password) throws IOException{
		users.add(new User(username, password));
//		for(User user : users)
//			System.out.println(user);
		this.isEmpty = false;
	}
	
	public static void writeAccount(Account account) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(account);
	}
	
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
	
	public boolean adminExists(){
		return admin != null ? true : false;
	}

	public List<User> getAllUsers() {
		return this.users;
	}
	
	public boolean deleteUser(User userToDelete){
		for(User user : users){
			if(user.equals(userToDelete)){
				users.remove(userToDelete);
				return true;
			}
		}
		System.out.println("User to delete was not found");
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
