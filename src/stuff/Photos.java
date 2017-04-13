package stuff;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main class
 * @author Steven Mattia, Gustavo Flores
 *
 */
public class Photos extends Application {
	
	/**
	 * Account which all data is written to over instances
	 */
	public static Account globalAccount;
	
	public static void main(String[] args){
		globalAccount = Account.getAccount();
		if(!globalAccount.adminExists()){
			globalAccount.admin = new Admin("admin", "admin");
			try {
				Account.writeAccount(globalAccount);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		launch(args);
	}
	
	public void start(Stage primaryStage) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/stuff/LoginPage.fxml"));
		
		Pane layout = (Pane)loader.load();
		
		LoginController controller = loader.getController();
		controller.start(primaryStage, layout);
		
		Scene scene = new Scene(layout, 650, 450);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
}