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

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String storeDir = "src/dat";
	public static final String storeFile = "users.dat";
	public User user;											//change this to a list
	
	public Account(){
		
	}
	
	public void addUser(String username, String password) throws IOException{
		user = new User(username, password);
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
			System.out.println("Account does not exist");
			return null;
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
		return null;
	}
	
	
}
