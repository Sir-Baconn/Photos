package stuff;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserCreateAlbumController {
	UserMainMenuController UserMM = new UserMainMenuController();
	
	@FXML
	private TextField albumNameTextField;
	
	@FXML
	private Label albumNameLabel;
	
	@FXML
	private Button createAlbumButton;
	
	private User loggedInUser;
	
	private ListView<Album> albumsListView;
	
	private ObservableList<Album> userAlbumsObsList;
	
	@FXML
	public void createAlbum(ActionEvent e){
		System.out.println("creating album...");
		//Stage currentWindow = (Stage)createAlbumButton.getScene().getWindow();
		Album album = new Album(albumNameTextField.getText());
		//PhotoAlbum.globalAccount.addAlbum(loggedInUser, album);
		
		loggedInUser.addAlbum(album);
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		userAlbumsObsList = FXCollections.observableArrayList(loggedInUser.albums);
		albumsListView.setItems(userAlbumsObsList);
		albumsListView.refresh();
		Stage stage = (Stage) createAlbumButton.getScene().getWindow();
		stage.close();//cannot close window this way or updating list does not work (must x out, try to fix later)
	}
	
	public void initialize(User currentUser, ListView<Album> albumsListView){
		this.loggedInUser = currentUser;
		this.albumsListView = albumsListView;
	}
	
}
