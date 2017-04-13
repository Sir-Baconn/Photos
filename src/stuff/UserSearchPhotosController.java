package stuff;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class UserSearchPhotosController {
	
	@FXML
	private TextField tagsTextField;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private Button createAlbumFromResultsButton;
	
	@FXML
	private DatePicker startDatePicker;
	
	@FXML
	private DatePicker endDatePicker;
	
	@FXML
	private Label startDateLabel;
	
	@FXML
	private Label endDateLabel;
	
	@FXML
	private Label tagsLabel;
	
	@FXML
	private Label photoResultsLabel;
	
	@FXML
	private ScrollPane imagePane;
	
	@FXML
	private FlowPane imagesBox;
	
	private List<ImageView> imageViews;
	
	private Album currentAlbum;

	
	@FXML
	private void searchPhotos(ActionEvent e){
		LocalDate localDateStart = startDatePicker.getValue();
		Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
		Date startDate = Date.from(instantStart);
		
		LocalDate localDateEnd = endDatePicker.getValue();
		Instant instantEnd = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
		Date endDate = Date.from(instantEnd);
		
		int j = 0;
		for(int i = 0; i < currentAlbum.getNumPhotos(); i++){
			Photo photo = currentAlbum.getPhotos().get(i);
			if(photo.getDateUploaded().after(startDate) && photo.getDateUploaded().before(endDate)){
				//System.out.println("found a photo");
				imageViews.add(new ImageView());
				imageViews.get(j).setImage(new Image("file:" + photo.getFilePath()));
				imageViews.get(j).setFitHeight(150);
				imageViews.get(j).setFitWidth(200);
				imagesBox.getChildren().add(imageViews.get(j));
				imagePane.setContent(imagesBox);
				j++;
			}
		}
	}
	
	@FXML
	private void createAlbum(ActionEvent e){
		
	}
	
	
	public void initialize(Album selectedItem) {
		this.currentAlbum = selectedItem;
		//System.out.println(currentAlbum.getNumPhotos());
		imageViews = new ArrayList<ImageView>(currentAlbum.getNumPhotos());
		double spacingFactor = 50;
		imagesBox.setHgap(spacingFactor);
		imagesBox.setVgap(50);
		imagesBox.setPadding(new Insets(10));
		imagePane.setPannable(true);
	}
	
	
	
}
