package stuff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login {
	Stage stage;
	public Login(Stage stage){
		this.stage = stage;
	}
	
	public void ToLogin(){
		Label LogLabel = new Label("Login");
		LogLabel.setLayoutX(300);
		LogLabel.setLayoutY(100);
		Label UserLabel = new Label("Username");
		UserLabel.setLayoutX(100);
		UserLabel.setLayoutY(200);
		Label PassLabel = new Label("Password");
		PassLabel.setLayoutX(100);
		PassLabel.setLayoutY(300);
		Button LoginBut = new Button("Login");
		LoginBut.setLayoutX(200);
		LoginBut.setLayoutY(400);
		Button CreateBut = new Button("Create Account");
		CreateBut.setLayoutX(300);
		CreateBut.setLayoutY(400);
		TextField UserText = new TextField();
		UserText.setLayoutX(200);
		UserText.setLayoutY(200);
		PasswordField PassText = new PasswordField();
		PassText.setLayoutX(200);
		PassText.setLayoutY(300);
		Pane layout = new Pane();
		Scene Login = new Scene(layout, 800, 450);
		layout.getChildren().addAll(LoginBut, CreateBut, UserText, PassText, LogLabel, UserLabel, PassLabel);
		stage.setScene(Login);
		stage.setTitle("Login");
		stage.show();
		
		CreateBut.setOnAction(e -> {
				Stage ConfirmBox = new Stage();
				FXMLLoader root = new FXMLLoader();
				root.setLocation(getClass().getResource("/stuff/Dialog.fxml"));
				
				
				try{
					Pane aa = (Pane) root.load();
					FXMLController controller = root.getController();
					Scene scene = new Scene(aa, 400,300);					
					ConfirmBox.setScene(scene);
					ConfirmBox.setTitle("Account setup");
					ConfirmBox.show();
					controller.initialize(UserText.getText(), PassText.getText());
					
				}catch(Exception err){
					
				}
				
		});
		
		LoginBut.setOnAction(e -> {
			if(UserText.getText().length() < 1 || PassText.getText().length() < 1){
				System.out.println("Please enter both a Username and Password.");
				return;
			}
			Account tempAcc = Account.getAccount();
			if(tempAcc != null && tempAcc.user.username.equals(UserText.getText())){
				System.out.println("Username found!!");
				//Name already taken label comes up
			}
		});
		
	}
	
}
