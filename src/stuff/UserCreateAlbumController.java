package stuff;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
		Album album = new Album(albumNameTextField.getText());
		loggedInUser.addAlbum(album);
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initialize(User currentUser){
		this.loggedInUser = currentUser;
	}
	
}
