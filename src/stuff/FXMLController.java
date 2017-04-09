package stuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLController {
	@FXML private Label Confirm;
	@FXML private Button Enter;
	@FXML private Button Cancel;
	@FXML private Label label;
	@FXML private TextField UserName;
	@FXML private PasswordField Password;
	@FXML private PasswordField PasswordConf;
	@FXML private Label NoMatch;
	@FXML private Label UserLab;
	@FXML private Label PassLab;
	@FXML private Label PassconfLab;
	@FXML private Label UsernameErr;
	@FXML private Label UsernameTaken;
	@FXML private Label Success;
	private Stage primaryStage;
	private Pane layout;

	
	@FXML
	private void enterConfirmPass(ActionEvent e){
		Stage currentWindow = (Stage)Enter.getScene().getWindow();
		
		reset();
		if(this.UserName.getText().equals("") || this.UserName.getText().equals(" ")){
			this.UsernameErr.setVisible(true);
			//Display error that name is required
			
		}else{
			if(UserName.getText().equals("admin")){
				System.out.println("You can not create an admin account. If you are the admin use admin credentials to log in.");
				return;
			}
			if(this.Password.getText().equals(this.PasswordConf.getText())){
				if(PhotoAlbum.globalAccount == null){
					System.out.println("we did something wrong");
				}
				//Account tempAcc = Account.getAccount();
				if(!PhotoAlbum.globalAccount.isEmpty && PhotoAlbum.globalAccount.userExists(UserName.getText(), Password.getText())){
					this.UsernameTaken.setVisible(true);
					//Name already taken label comes up
				}else{
					System.out.println("entered");
					try{
						PhotoAlbum.globalAccount.addUser(this.UserName.getText(), this.Password.getText());
						Account.writeAccount(PhotoAlbum.globalAccount);
						this.Success.setVisible(true);
					}catch(Exception ez){
						ez.printStackTrace();
					}
				}
			}
			currentWindow.close();
		}
		
		
	}
	
	public void initialize(String UserName, String Password){
		this.Success.setVisible(false);
		this.NoMatch.setVisible(false);
		this.UsernameErr.setVisible(false);
		this.UsernameTaken.setVisible(false);
		this.UserName.setText(UserName);
		this.Password.setText(Password);
	}
	
	public void initialize(){
		this.Success.setVisible(false);
		this.NoMatch.setVisible(false);
		this.UsernameErr.setVisible(false);
		this.UsernameTaken.setVisible(false);
	}
	
	@FXML
	private void cancelConfirmPass(ActionEvent e){
		Stage stage = (Stage)Cancel.getScene().getWindow();
		stage.close();
	}
	
	private void reset(){
		this.Success.setVisible(false);
		this.NoMatch.setVisible(false);
		this.UsernameErr.setVisible(false);
		this.UsernameTaken.setVisible(false);
	}

	public void start(Stage primaryStage, Pane layout) {
		this.primaryStage = primaryStage;
		this.layout = layout;
	}
	
	
}
