package com.zuji.util.examPaper;

import java.awt.image.BufferedImage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static java.lang.System.*;

public class ImageViewerController implements ChangeListener {

    @FXML
    private Slider slider;
    @FXML
    private ImageView imageView;

    @FXML
	private void initialize() {    
    	slider.valueProperty().addListener(this);
    }


	public void changed (ObservableValue arg0, Object oldValue, Object newValue) {
		int intValue = ((Double)newValue).intValue();
		slider.setValue(intValue);
		ImageEngine engine = ImageEngine.getInstance();
		if (engine.getOriginalImage()!=null) {
			Image modifiedImage = engine.erase(intValue);
			imageView.setImage(modifiedImage);
		}
	}


}
