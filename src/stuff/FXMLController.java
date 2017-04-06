package stuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
	Account acc = new Account();
	
	
	@FXML
	private void enterConfirmPass(ActionEvent e){
		reset();
		if(this.UserName.getText().equals("") || this.UserName.getText().equals(" ")){
			this.UsernameErr.setVisible(true);
			//Display error that name is required
			
		}else{
			if(this.Password.getText().equals(this.PasswordConf.getText())){
				if(acc.getUser(this.UserName.getText())){
					this.UsernameTaken.setVisible(true);
					//Name already taken label comes up
				}else{
					try{
						acc.addUser(this.UserName.getText(), this.Password.getText());
						this.Success.setVisible(true);
					}catch(Exception ez){}
				}
			}
		}
		
		
	}
	
	@FXML
	public void initialize(String UserName, String Password){
		this.Success.setVisible(false);
		this.NoMatch.setVisible(false);
		this.UsernameErr.setVisible(false);
		this.UsernameTaken.setVisible(false);
		this.UserName.setText(UserName);
		this.Password.setText(Password);
	}
	
	@FXML
	private void cancelConfirmPass(ActionEvent e){
		if(this.Password.getText().equals(this.PasswordConf.getText())){
			System.out.println("Matching passwords");
		}else{
			this.NoMatch.setVisible(true);
		}
	}
	
	private void reset(){
		this.Success.setVisible(false);
		this.NoMatch.setVisible(false);
		this.UsernameErr.setVisible(false);
		this.UsernameTaken.setVisible(false);
	}
	
	
}
