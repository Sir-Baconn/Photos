package stuff;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PhotoAlbum extends Application {
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage PrimaryStage) throws IOException{
		Login login = new Login(PrimaryStage);
		login.ToLogin();
		Account acc = new Account();
//		Login LogMenu = new Login(PrimaryStage);
//		LogMenu.ToLogin();
	}
	
	
	
}
