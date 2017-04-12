package stuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserAlbumController {
	
	@FXML
	private Button addPhotoButton;
	
	@FXML
	private Button deletePhotoButton;
	
	@FXML
	private Button captionPhotoButton;
	
	@FXML
	private Button focusPhotoButton;
	
	@FXML
	private Button addTagsButton;
	
	@FXML
	private Button removeTagsButton;
	
	@FXML
	private Button copyPhotoButton;
	
	@FXML
	private Button movePhotoButton;
	
	@FXML
	private Button startSlideshowButton;
	
	@FXML
	private Text albumNameText;
	
	private Album currentAlbum;
	private User loggedInUser;
	
	@FXML
	private void addPhoto(ActionEvent e){
		
	}
	
	@FXML
	private void deletePhoto(ActionEvent e){
		
	}
	
	@FXML
	private void captionPhoto(ActionEvent e){
		
	}
	
	@FXML
	private void focusPhoto(ActionEvent e){
		
	}
	
	@FXML
	private void addTags(ActionEvent e){
		
	}
	
	@FXML
	private void removeTags(ActionEvent e){
		
	}
	
	@FXML
	private void copyPhoto(ActionEvent e){
		
	}
	
	@FXML
	private void movePhoto(ActionEvent e){
		
	}
	
	@FXML
	private void startSlideshow(ActionEvent e){
		
	}
	
	public void initialize(User loggedInUser, Album currentAlbum){
		this.loggedInUser = loggedInUser;
		this.currentAlbum = currentAlbum;
	}
	
	public void start(Stage primaryStage, Pane layout){
		
	}
	
}
