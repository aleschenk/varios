package com.javafx;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CropImage extends Application implements Initializable {

  @FXML
  private ImageView imageView;

  @FXML
  private Slider widthSlider;

  @FXML
  private Slider heightSlider;

  @FXML
  private Slider vXSlider;

  @FXML
  private Slider vYSlider;

  @FXML
  private Slider vWidthSlider;

  @FXML
  private Slider vHeightSlider;

  @FXML
  private CheckBox preserveRatioCheckBox;

  @FXML
  private Label vxLabel;

  @FXML
  private Label vyLabel;

  @FXML
  private Label vwLabel;

  @FXML
  private Label vhLabel;

  @Override
  public void start(final Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("CropImage.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
//    vXSlider.setValue(imageView.getViewport().getMinX());
//    vYSlider.setValue(imageView.getViewport().getMinY());
//    vWidthSlider.setValue(imageView.getViewport().getWidth());
//    vHeightSlider.setValue(imageView.getViewport().getHeight());
    widthSlider.setValue(imageView.getFitWidth());
    heightSlider.setValue(imageView.getFitHeight());
    preserveRatioCheckBox.setSelected(imageView.isPreserveRatio());
    preserveRatioCheckBox.setOnAction(event -> {
      imageView.setPreserveRatio(preserveRatioCheckBox.isSelected());
    });

    widthSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      imageView.setFitWidth(newValue.doubleValue());
    });

    heightSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      imageView.setFitHeight(newValue.doubleValue());
    });

    vXSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vxLabel.setText("vX: " + newValue);
//      imageView.setViewport(new Rectangle2D(newValue.doubleValue(), imageView.getViewport().getMinY(), imageView.getViewport().getWidth(), imageView.getViewport().getHeight()));
    });

    vYSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vyLabel.setText("vY: " + newValue);
//      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), newValue.doubleValue(), imageView.getViewport().getWidth(), imageView.getViewport().getHeight()));
    });

    vWidthSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vwLabel.setText("vW: " + newValue);
//      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), imageView.getViewport().getMinY(), newValue.doubleValue(), imageView.getViewport().getHeight()));
    });

    vHeightSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vhLabel.setText("vH: " + newValue);
//      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), imageView.getViewport().getMinY(), imageView.getViewport().getWidth(), newValue.doubleValue()));
    });
  }

}
