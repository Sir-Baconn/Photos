package stuff;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Account implements Serializable{
	File Accounts = new File("Accounts.txt");
	ObjectOutputStream out;
	ObjectInputStream in;
	public Account(){
		try{
			Accounts.createNewFile();
			out = new ObjectOutputStream(new FileOutputStream(Accounts, true));
			in = new ObjectInputStream(new FileInputStream(Accounts));
		}catch(Exception e){
			
		}
		
	}
	
	public void addUser(String Username, String password) throws IOException{
		
		out.writeObject(Username);
		out.writeObject(password);
		out.close();
	}
	
	public boolean getUser(String UserName){
		int count = 0;
		
		try {
			
			while(true){
				
				if(in.readObject().equals(UserName) && count%2 == 0){
					in.close();
					return true;
				}
				System.out.println("Test");
				System.out.println(in);
					count++;
			}
			
			
			
		} catch (IOException e) {	
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
}
