package stuff;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminController {
	@FXML
	private Pane layout;
	
	@FXML
	private ComboBox<String> adminCombo;
	
	@FXML
	private Label userListLabel;
	
	@FXML
	private ListView<User> userListView;
	
	@FXML
	private Button deleteButton;
	
	private ObservableList<User> usersObsList;

	private Stage primaryStage;
	
	//TODO: Listview might want to only show usernames instead of both username and passwords
	
	public void initialize(){
		deleteButton.setVisible(false);
		usersObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUsers());
		listAllUsers();
		adminCombo.valueProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				switch(observable.getValue().toString()){
					case "List Users":
						usersObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUsers());
						if(!userListView.isVisible()){
							userListView.setVisible(true);
						}
						if(!userListLabel.isVisible()){
							userListLabel.setVisible(true);
						}
						if(deleteButton.isVisible()){
							deleteButton.setVisible(false);
						}
						
						userListLabel.setText("Current Users");
						
						listAllUsers();
						break;
					case "Create User":
						//recreate create account page
						if(userListView.isVisible()){
							userListView.setVisible(false);
						}
						if(userListLabel.isVisible()){
							userListLabel.setVisible(false);
						}
						if(deleteButton.isVisible()){
							deleteButton.setVisible(false);
						}
						createAccount();
						break;
					case "Delete User":
						if(!userListView.isVisible()){
							userListView.setVisible(true);
						}
						if(!userListLabel.isVisible()){
							userListLabel.setVisible(true);
						}
						if(!deleteButton.isVisible()){
							deleteButton.setVisible(true);
						}
						
						userListLabel.setText("Current Users (select one to delete)");
						
						if(userListView.getSelectionModel().getSelectedItem() != null){
							System.out.println("ayyyyyyyyyyyyyyyyyy");
							userListView.getSelectionModel().clearSelection();
						}
							
						userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>(){
							@Override
							public void changed(ObservableValue<? extends User> observable1, User oldValue,
									User newValue) {
								if(observable.getValue().toString().equals("List Users"))
									return;
								deleteButton.setVisible(true);
							}
							
						});
						break;
					default:
						break;
				}
			}
			
		});
	}
	
	public void createAccount(){
		Stage confirmBox = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/Dialog.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			FXMLController controller = root.getController();
			controller.start(this.primaryStage, pane);
			Scene scene = new Scene(pane, 400, 300);
			confirmBox.initModality(Modality.WINDOW_MODAL);
			confirmBox.initOwner(primaryStage);
			confirmBox.setScene(scene);
			confirmBox.setTitle("Account Setup");
			confirmBox.show();
			controller.initialize();
		}catch(Exception error){
			error.printStackTrace();
		}
	}
	
	@FXML
	private void deleteUser(ActionEvent e){
		User userToDelete = new User(userListView.getSelectionModel().getSelectedItem().username, userListView.getSelectionModel().getSelectedItem().password);
		System.out.println("User selected: " + userToDelete);
		PhotoAlbum.globalAccount.deleteUser(userToDelete);
		usersObsList = FXCollections.observableArrayList(PhotoAlbum.globalAccount.getAllUsers());
		listAllUsers();
		Account.clearAccountData();
		try {
			Account.writeAccount(PhotoAlbum.globalAccount);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	protected void listAllUsers() {
		userListView.setItems(usersObsList);
	}

	public void start(Stage primaryStage, Pane layout) {
		this.primaryStage = primaryStage;
		this.layout = layout;
	}
	
}
