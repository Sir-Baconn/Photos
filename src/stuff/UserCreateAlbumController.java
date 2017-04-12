package stuff;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserCreateAlbumController {
	
	@FXML
	private TextField albumNameTextField;
	
	@FXML
	private Label albumNameLabel;
	
	@FXML
	private Button createAlbumButton;
	
	private User loggedInUser;
	
	@FXML
	public void createAlbum(ActionEvent e){
		System.out.println("creating album...");
		//Stage currentWindow = (Stage)createAlbumButton.getScene().getWindow();
		Album album = new Album(albumNameTextField.getText());
		PhotoAlbum.globalAccount.addAlbum(loggedInUser, album);
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//currentWindow.close();											//cannot close window this way or updating list does not work (must x out, try to fix later)
	}
	
	public void initialize(User currentUser){
		this.loggedInUser = currentUser;
	}
	
}
