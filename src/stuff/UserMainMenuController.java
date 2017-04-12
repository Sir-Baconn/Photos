package stuff;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	private TextField renameTextField;
	
	@FXML
	private Button submitRenameButton;
	
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
//		for(Album album : loggedInUser.albums){
//			System.out.println("Album: " + album.getName());
//		}
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
			controller.initialize(loggedInUser, albumsListView);
		}catch(Exception error){
			error.printStackTrace();
		}
		
//		confirmBox.setOnCloseRequest(new EventHandler<WindowEvent>(){
//
//			@Override
//			public void handle(WindowEvent arg0) {
//				userAlbumsObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUserAlbums(loggedInUser));
//				albumsListView.setItems(userAlbumsObsList);
//			}
//			
//		});
		
	}
	
	@FXML
	private void deleteAlbum(ActionEvent e){
		if(albumsListView.getSelectionModel().getSelectedItem() == null){
			System.out.println("You must select an album first!");
			return;
		}
		userAlbumsObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUserAlbums(loggedInUser));
		Album albumToDelete = new Album(albumsListView.getSelectionModel().getSelectedItem().getName());
		if(albumsListView.getSelectionModel().getSelectedItem() != null){
			albumsListView.getSelectionModel().clearSelection();
		}
		System.out.println("Album selected: " + albumToDelete);
		PhotoAlbum.globalAccount.deleteAlbum(loggedInUser, albumToDelete);
		userAlbumsObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUserAlbums(loggedInUser));
		listAllAlbums();
		Account.clearAccountData();
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	@FXML
	private void renameAlbum(ActionEvent e){
		if(albumsListView.getSelectionModel().getSelectedItem() == null){
			System.out.println("Select an album first");
			return;
		}
		System.out.println("Albums are: ");
		for(Album album : userAlbumsObsList){
			System.out.println(album.toString());
		}
		
		renameTextField.setVisible(true);
		submitRenameButton.setVisible(true);
	}
	
	@FXML
	private void submitRename(ActionEvent e){
		if(renameTextField.getText().length() < 1){
			System.out.println("Enter a name please");
			return;
		}
		
		albumsListView.getSelectionModel().getSelectedItem().setName(renameTextField.getText());
		albumsListView.refresh();
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
		userAlbumsObsList = FXCollections.observableArrayList(loggedInUser.albums);
		renameTextField.clear();
		renameTextField.setVisible(false);
		submitRenameButton.setVisible(false);
		albumsListView.setItems(userAlbumsObsList);
	}
	
	@FXML
	private void openAlbum(ActionEvent e){
		Stage albumStage = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/UserAlbumScreen.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			UserAlbumController controller = root.getController();
			//controller.start(this.primaryStage, pane);
			Scene scene = new Scene(pane, 1212, 747);
			albumStage.setScene(scene);
			albumStage.setTitle("Album");
			albumStage.show();
			controller.initialize(loggedInUser, albumsListView.getSelectionModel().getSelectedItem());
		}catch(Exception error){
			error.printStackTrace();
		}
	}
	
	@FXML
	private void searchPhotos(ActionEvent e){
		
	}
	
	protected void listAllAlbums() {
		albumsListView.setItems(userAlbumsObsList);
	}
	
	public void addAlbumToList(Album album){
		
	}
	
	public void initialize(String username, String password){
		numPhotosLabel.setVisible(false);
		earliestDateLabel.setVisible(false);
		latestDateLabel.setVisible(false);
		albumNameLabel.setVisible(false);
		renameTextField.setVisible(false);
		submitRenameButton.setVisible(false);
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
				if(newValue == null)			//changed is called on delete for some reason even though we clear selections and this becomes null so exit, otherwise there is an exception
					return;
				numPhotosLabel.setText("Number of Photos: " + newValue.getNumPhotos());
				earliestDateLabel.setText("Date of earliest photo: " + " no date implementation yet");
				latestDateLabel.setText("Date of latest photo: " + " no date implementation yet");
				albumNameLabel.setText("Album Name: " + newValue.getName());
				
				numPhotosLabel.setVisible(true);
				earliestDateLabel.setVisible(true);
				latestDateLabel.setVisible(true);
				albumNameLabel.setVisible(true);
			}
			
		});
		
	}
	
	public void refresh(){
		albumsListView.refresh();
	}

}
