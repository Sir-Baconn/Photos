package stuff;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
	private ScrollPane imagePane;
	
	@FXML
	private FlowPane imagesBox;
	
	private List<ImageView> imageViews;
	
	private Album currentAlbum;
	private User loggedInUser;
	
	private Pane layout;
	
	private Photo photoClicked;
	
	
	@FXML
	private void addPhoto(ActionEvent e) throws IOException{
		FileChooser choose = new FileChooser();
		choose.setTitle("Open Resource File");
		choose.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		Stage stage = (Stage) movePhotoButton.getScene().getWindow();
		File selectedFile = choose.showOpenDialog(stage);
		
		if (selectedFile != null) {
			String path = selectedFile.getPath();
			Photo photo = new Photo(path);
			Date dateCaptured = new Date(selectedFile.lastModified());
			photo.setDateUploaded(dateCaptured);
			imageViews.add(new ImageView());
			imageViews.get(currentAlbum.getNumPhotos()).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent arg0) {
					ImageView img = (ImageView)arg0.getSource();
					//System.out.println(img.getImage().toString());
					//System.out.println(imageViews.indexOf(img));
					photoClicked = currentAlbum.getPhotos().get(imageViews.indexOf(img));
				}
			});
			imageViews.get(currentAlbum.getNumPhotos()).setImage(new Image("file:" + photo.getFilePath()));
			imageViews.get(currentAlbum.getNumPhotos()).setFitHeight(150);
			imageViews.get(currentAlbum.getNumPhotos()).setFitWidth(200);
			imagesBox.getChildren().add(imageViews.get(currentAlbum.getNumPhotos()));
			imagePane.setContent(imagesBox);
			currentAlbum.addPhotoToAlbum(photo);
			Account.writeAccount(Photos.globalAccount);
		}
		
		
	}
	
	@FXML
	private void deletePhoto(ActionEvent e){
		if(photoClicked == null)
			return;
		
		int indexToDelete = currentAlbum.getPhotos().indexOf(photoClicked);
		currentAlbum.getPhotos().remove(photoClicked);
		imagesBox.getChildren().removeAll(imageViews);
		imageViews.remove(indexToDelete);
		imagesBox.getChildren().addAll(imageViews);
		imagePane.setContent(imagesBox);
		Account.clearAccountData();
		try {
			Account.writeAccount(Photos.globalAccount);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
		
		photoClicked = null;
	}
	
	@FXML
	private void captionPhoto(ActionEvent e){
		
	}
	
	@FXML
	private void focusPhoto(ActionEvent e){
		if(photoClicked == null){
			//System.out.println("Click on a photo first");
			return;
		}
		
		Stage photoStage = new Stage();
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("/stuff/UserFocusPhotoScreen.fxml"));
		
		try{
			Pane pane = (Pane) root.load();
			UserFocusPhotoController controller = root.getController();
			controller.start(pane);
			Scene scene = new Scene(pane, 558.0, 618.0);
			photoStage.setScene(scene);
			photoStage.setTitle("Album");
			photoStage.show();
			controller.initialize(photoClicked);
		}catch(Exception error){
			error.printStackTrace();
		}
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
		this.photoClicked = null;
		this.loggedInUser = loggedInUser;
		this.currentAlbum = currentAlbum;
		this.albumNameText.setText(currentAlbum.toString());
		imageViews = new ArrayList<ImageView>(currentAlbum.getNumPhotos());
		double spacingFactor = 50;
		imagesBox.setHgap(spacingFactor);
		imagesBox.setVgap(50);
		imagesBox.setPadding(new Insets(10));
		imagePane.setPannable(true);
		for(int i = 0; i < currentAlbum.getNumPhotos(); i++){
			Photo photo = currentAlbum.getPhotos().get(i);
			//System.out.println(photo.getFilePath());
			imageViews.add(new ImageView());
			imageViews.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent arg0) {
					ImageView img = (ImageView)arg0.getSource();
					//System.out.println(img.getImage().toString());
					//System.out.println(imageViews.indexOf(img));
					photoClicked = currentAlbum.getPhotos().get(imageViews.indexOf(img));
				}
			});
			imageViews.get(i).setImage(new Image("file:" + photo.getFilePath()));
			imageViews.get(i).setFitHeight(150);
			imageViews.get(i).setFitWidth(200);
			imagesBox.getChildren().add(imageViews.get(i));
			imagePane.setContent(imagesBox);
		}
	}
	
	public void start(Pane layout){
		this.layout = layout;
	}
	
}
