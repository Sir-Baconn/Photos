package stuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
	
	
	@FXML
	private void login(ActionEvent e){
		System.out.println("entered");
		if(userTextField.getText().length() < 1 || passwordTextField.getText().length() < 1){
			System.out.println("Please enter both a Username and Password.");
			return;
		}
		Account tempAcc = Account.getAccount();
		if(tempAcc != null && tempAcc.user.username.equals(userTextField.getText()) && tempAcc.user.password.equals(passwordTextField.getText())){
			System.out.println("User found!");
			System.out.println(tempAcc.user);
		}else{
			System.out.println("Username or password is invalid.");
		}
	}
	
	@FXML
	private void createAccount(ActionEvent e){
		Stage confirmBox = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/Dialog.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			FXMLController controller = root.getController();
			Scene scene = new Scene(pane, 400, 300);
			confirmBox.setScene(scene);
			confirmBox.setTitle("Account Setup");
			confirmBox.show();
			controller.initialize(userTextField.getText(), passwordTextField.getText());
		}catch(Exception error){
			error.printStackTrace();
		}
	}

	public void start(Stage primaryStage, Pane layout) {
		this.layout = layout;
	}
}
