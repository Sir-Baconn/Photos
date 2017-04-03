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
	Account acc = new Account();
	
	
	@FXML
	private void enterConfirmPass(ActionEvent e){
		try{
			acc.addUser(this.UserName.getText(), this.Password.getText());
			acc.getUser();
		}catch(Exception ez){}
		
	}
	
	@FXML
	public void initialize(String UserName, String Password){
		this.NoMatch.setVisible(false);
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
	
	
}
