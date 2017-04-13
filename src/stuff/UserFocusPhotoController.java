package stuff;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UserFocusPhotoController {
	
	@FXML
	private Pane layout;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Label captionLabel;
	
	@FXML
	private Label tagsLabel;
	
	@FXML
	private Label dateLabel;
	
	
	public void initialize(Photo photo){
		imageView.setImage(new Image("file:" + photo.getFilePath()));
		if(photo.getCaption() != null)
			this.captionLabel.setText("Caption: " + photo.getCaption());
		if(photo.getTags() != null && photo.getTags().values().size() > 0){
			ArrayList<String> tags = new ArrayList<String>(photo.getTags().values());
			String tagsAsOne = "";
			for(String tag : tags){
				tagsAsOne += tag;
			}
			this.tagsLabel.setText("Tags: " + tagsAsOne);
		}
		if(photo.getDateUploaded() != null)
			this.dateLabel.setText("Date Created: " + photo.getDateUploaded());
	}


	public void start(Pane pane) {
		this.layout = pane;
	}
	
}
