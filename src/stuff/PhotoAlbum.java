package stuff;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PhotoAlbum extends Application {
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/stuff/LoginPage.fxml"));
		
		Pane layout = (Pane)loader.load();
		
		LoginController controller = loader.getController();
		controller.start(primaryStage, layout);
		
		Scene scene = new Scene(layout, 800, 450);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
}
