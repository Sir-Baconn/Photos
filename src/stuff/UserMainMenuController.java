package stuff;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserMainMenuController {
	
	@FXML
	private ListView<String> albumsListView;								//String is temp, should probably be album class
	
	@FXML
	private Label albumsListViewLabel;
	
	@FXML
	private Text loggedInAsText;
	
	@FXML
	private Label numPhotosLabel;
	
	@FXML
	private Label earliestDateLabel;
	
	@FXML
	private Label latestDateLabel;
	
	@FXML
	private Label albumNameLabel;
	
	@FXML
	private Button addAlbumButton;
	
	@FXML
	private Button deleteAlbumButton;
	
	@FXML
	private Button renameAlbumButton;
	
	@FXML
	private Button openAlbumButton;
	
	@FXML
	private Button searchPhotosButton;
	
	private ObservableList<String> userAlbumsObsList;						//String is temp, should probably be album class
	
	private Stage loginStage;
	
	@FXML
	private void addAlbum(ActionEvent e){
		
	}
	
	@FXML
	private void deleteAlbum(ActionEvent e){
		
	}
	
	@FXML
	private void renameAlbum(ActionEvent e){
		
	}
	
	@FXML
	private void openAlbum(ActionEvent e){
		
	}
	
	@FXML
	private void searchPhotos(ActionEvent e){
		
	}
	
	public void initialize(String username, String password){
		User loggedInUser = new User(username, password);
		loggedInAsText.setText(loggedInAsText.getText() + loggedInUser.username);
	}
	
	public void start(Pane layout, Stage loginStage){
		this.loginStage = loginStage;
	}

}
