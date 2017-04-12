package stuff;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	
	@FXML
	private ImageView previewImage;
	
	private Album currentAlbum;
	private User loggedInUser;
	
	@FXML
	private void addPhoto(ActionEvent e) throws IOException{
		FileChooser choose = new FileChooser();
		choose.setTitle("Open Resource File");
		 choose.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		 Stage stage = (Stage) movePhotoButton.getScene().getWindow();
		 File selectedFile = choose.showOpenDialog(stage);
		
		 if (selectedFile != null) {
			 selectedFile.createNewFile();
			 FileWriter fw = new FileWriter(selectedFile);
			 fw.write(selectedFile.getName());
//			saveFileRoutine(selectedFile);
		 }
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
		this.previewImage.setVisible(false);
		this.loggedInUser = loggedInUser;
		this.currentAlbum = currentAlbum;
	}
	
	public void start(Stage primaryStage, Pane layout){
		
	}
	
	private void storeImage(File file){
//        try {
//        	
//               
//            
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
         
    }
	
}
