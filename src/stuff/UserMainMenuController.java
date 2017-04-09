package stuff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserMainMenuController {
	
	@FXML
	private ListView<Album> albumsListView;								//String is temp, should probably be album class
	
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
	
	private ObservableList<Album> userAlbumsObsList;						//String is temp, should probably be album class
	
	private Stage loginStage;
	
	private Pane layout;
	
	private User loggedInUser;
	
	@FXML
	private void addAlbum(ActionEvent e){
		for(Album album : loggedInUser.albums){
			System.out.println("Album: " + album.name);
		}
		Stage confirmBox = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/UserCreateAlbumScreen.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			UserCreateAlbumController controller = root.getController();
			Scene scene = new Scene(pane, 346, 133);
			confirmBox.setScene(scene);
			confirmBox.setTitle("Add Album");
			confirmBox.show();
			controller.initialize(loggedInUser);
		}catch(Exception error){
			error.printStackTrace();
		}
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
		numPhotosLabel.setVisible(false);
		earliestDateLabel.setVisible(false);
		latestDateLabel.setVisible(false);
		albumNameLabel.setVisible(false);
		loggedInUser = PhotoAlbum.globalAccount.getUser(username, password);
		userAlbumsObsList = FXCollections.observableArrayList(loggedInUser.albums);
		albumsListView.setItems(userAlbumsObsList);
		loggedInAsText.setText(loggedInAsText.getText() + loggedInUser.username);
	}
	
	public void start(Pane layout, Stage loginStage){
		this.loginStage = loginStage;
		this.layout = layout;
		
		albumsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Album>(){
			@Override
			public void changed(ObservableValue<? extends Album> observable, Album oldValue, Album newValue) {
				numPhotosLabel.setText(numPhotosLabel.getText() + " " + newValue.getNumPhotos());
				earliestDateLabel.setText(earliestDateLabel.getText() + " no date implementation yet");
				latestDateLabel.setText(latestDateLabel.getText() + " no date implementation yet");
				albumNameLabel.setText(albumNameLabel.getText() + " " + newValue.name);
				
				numPhotosLabel.setVisible(true);
				earliestDateLabel.setVisible(true);
				latestDateLabel.setVisible(true);
				albumNameLabel.setVisible(true);
			}
			
		});
		
	}

}
