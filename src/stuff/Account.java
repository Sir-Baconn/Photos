package stuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Account {
	File Accounts = new File("Accounts.txt");
	FileOutputStream FO;
	FileInputStream FI;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Account(){
		try{
			FO = new FileOutputStream(Accounts, true);
			out = new ObjectOutputStream(FO);
			FI = new FileInputStream(Accounts);
			Accounts.createNewFile();
		}catch(Exception e){
			
		}
		
	}
	
	public void addUser(String Username, String password) throws IOException{
		out.writeObject(Username);
		out.writeObject(password);
	}
	
	public void getUser(){
		try {
			in = new ObjectInputStream(FI);
			System.out.println(in.readObject());
			System.out.println(in.readObject());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
