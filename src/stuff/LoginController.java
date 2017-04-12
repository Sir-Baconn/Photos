package stuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private Pane layout;
	
	@FXML
	private Label logLabel;
	
	@FXML
	private Label userLabel;
	
	@FXML
	private Label passLabel;
	
	@FXML
	private Button loginBut;
	
	@FXML
	private Button createBut;
	
	@FXML
	private TextField userTextField;
	
	@FXML
	private PasswordField passwordTextField;

	private Stage primaryStage;
	
	
	@FXML
	private void login(ActionEvent e){
		Stage currentWindow = (Stage)loginBut.getScene().getWindow();
		System.out.println("entered");
		if(userTextField.getText().length() < 1 || passwordTextField.getText().length() < 1){
			System.out.println("Please enter both a Username and Password.");
			return;
		}
		
		if(Admin.isAdmin(userTextField.getText(), passwordTextField.getText())){
			System.out.println("Admin access approved");
			loadAdminPage();
			return;
		}
		//Account tempAcc = Account.getAccount();
		if(!PhotoAlbum.globalAccount.isEmpty && PhotoAlbum.globalAccount.userExists(userTextField.getText(), passwordTextField.getText())){
			System.out.println("User found!");
			loadUserPage();
			currentWindow.close();
			//return;?
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Invalid Credentials");
			alert.setContentText("Username or password is incorrect");
			alert.showAndWait();
			System.out.println("Username or password is invalid.");
		}
	}
	
	@FXML
	public void createAccount(ActionEvent e){
		Stage confirmBox = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/Dialog.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			FXMLController controller = root.getController();
			Scene scene = new Scene(pane, 400, 300);
			confirmBox.initModality(Modality.WINDOW_MODAL);
			confirmBox.initOwner(primaryStage);
			confirmBox.setScene(scene);
			confirmBox.setTitle("Account Setup");
			confirmBox.show();
			controller.initialize(userTextField.getText(), passwordTextField.getText());
		}catch(Exception error){
			error.printStackTrace();
		}
	}
	
	private void loadAdminPage(){
		Stage adminStage = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/AdminScreen.fxml"));
		
		try{
			Pane pane = (Pane)root.load();
			AdminController controller  = root.getController();
			controller.start(this.primaryStage, pane);
			Scene scene = new Scene(pane, 468, 439);
			adminStage.initModality(Modality.WINDOW_MODAL);
			adminStage.initOwner(primaryStage);
			adminStage.setScene(scene);
			adminStage.setTitle("Admin Page");
			adminStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void loadUserPage(){
		Stage userStage = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/UserScreen.fxml"));
		
		try{
			Pane pane = (Pane)root.load();
			UserMainMenuController controller  = root.getController();
			controller.start(pane, this.primaryStage);
			controller.initialize(userTextField.getText(), passwordTextField.getText());
			Scene scene = new Scene(pane, 760, 549);
			userStage.setScene(scene);
			userStage.setTitle("User Page");
			userStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void start(Stage primaryStage, Pane layout) {
		this.primaryStage = primaryStage;
		this.layout = layout;
	}
}
