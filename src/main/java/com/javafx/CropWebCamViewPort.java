package com.javafx;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.script.Bindings;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class CropWebCamViewPort extends Application implements Initializable {

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
  private ToggleButton cameraButton;

  @FXML
  private Label widthLabel;

  @FXML
  private Label heightLabel;

  @FXML
  private Label vxLabel;

  @FXML
  private Label vyLabel;

  @FXML
  private Label vwLabel;

  @FXML
  private Label vhLabel;

  @FXML
  private Label ihLabel;

  @FXML
  private Label iwLabel;

  @FXML
  private Label realVHLabel;

  @FXML
  private Label realVWLabel;

  @FXML
  private Rectangle viewportRectangle;

  private Webcam webCam = null;
  private boolean stopCamera = false;
  private ObjectProperty<Image> imageProperty = new SimpleObjectProperty();
  private SimpleObjectProperty<String> widthProperty = new SimpleObjectProperty<>();

  @Override
  public void start(final Stage stage) throws Exception {
    Webcam.setDriver(new V4l4jDriver());
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("CropWebCamViewPort.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cameraButton.setOnAction(event -> {
      if(cameraButton.isSelected()) {
        initializeWebCam();
        cameraButton.setText("Turn off camera");
      } else {
        cameraButton.setText("Turn on camera");
        stop();
      }
    });

    imageView.setViewport(new Rectangle2D(1,1,640,120));

    vXSlider.setValue(imageView.getViewport().getMinX());
    vxLabel.setText("vX " + vXSlider.getValue());

    vYSlider.setValue(imageView.getViewport().getMinY());
    vyLabel.setText("vY " + vYSlider.getValue());

    vWidthSlider.setValue(imageView.getViewport().getWidth());
    vwLabel.setText("vWidth " + vWidthSlider.getValue());

    vHeightSlider.setValue(imageView.getViewport().getHeight());
    vhLabel.setText("vHeight " + vHeightSlider.getValue());

    widthSlider.setValue(imageView.getFitWidth());
    widthLabel.setText("Width: " + widthSlider.getValue());

    heightSlider.setValue(imageView.getFitHeight());
    heightLabel.setText("Height " + heightSlider.getValue());

    imageView.setPreserveRatio(true);


    preserveRatioCheckBox.setSelected(imageView.isPreserveRatio());
    preserveRatioCheckBox.setOnAction(event -> {
      imageView.setPreserveRatio(preserveRatioCheckBox.isSelected());
    });

    viewportRectangle.setLayoutX(imageView.getLayoutX());
    viewportRectangle.setLayoutY(imageView.getLayoutY());
    viewportRectangle.setWidth(imageView.getFitWidth());
    viewportRectangle.setHeight(imageView.getFitHeight());

    widthSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      widthLabel.setText("Width: " + newValue.intValue());
      imageView.setFitWidth(newValue.doubleValue());
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });

    heightSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      heightLabel.setText("Height: " + newValue.intValue());
      imageView.setFitHeight(newValue.doubleValue());
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });
//
    vXSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vxLabel.setText("vX: " + newValue.intValue());
      imageView.setViewport(new Rectangle2D(newValue.doubleValue(), imageView.getViewport().getMinY(), imageView.getViewport().getWidth(), imageView.getViewport().getHeight()));
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });

    vYSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vyLabel.setText("vY: " + newValue.intValue());
      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), newValue.doubleValue(), imageView.getViewport().getWidth(), imageView.getViewport().getHeight()));
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });

    vWidthSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vwLabel.setText("vWidth: " + newValue.intValue());
      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), imageView.getViewport().getMinY(), newValue.doubleValue(), imageView.getViewport().getHeight()));
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });

    vHeightSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      vhLabel.setText("vHeight: " + newValue.intValue());
      imageView.setViewport(new Rectangle2D(imageView.getViewport().getMinX(), imageView.getViewport().getMinY(), imageView.getViewport().getWidth(), newValue.doubleValue()));
      iwLabel.setText("iW: " + imageView.getFitWidth());
      ihLabel.setText("iH: " + imageView.getFitHeight());
      realVWLabel.setText("rWH: " + imageView.getViewport().getWidth());
      realVHLabel.setText("rVH: " + imageView.getViewport().getHeight());
      viewportRectangle.setLayoutX(imageView.getLayoutX());
      viewportRectangle.setLayoutY(imageView.getLayoutY());
      viewportRectangle.setWidth(imageView.getFitWidth());
      viewportRectangle.setHeight(imageView.getFitHeight());
    });

  }

  public void initializeWebCam() {
    final int webCamIndex = 0;

    Task<Void> webCamTask = new Task<Void>() {

      @Override
      protected Void call() {

        if (webCam != null) {

          stop();
        }

        webCam = Webcam.getWebcams().get(webCamIndex);
        webCam.setViewSize(new Dimension(640,480));
        webCam.open();


        start();

        return null;
      }
    };

    Thread webCamThread = new Thread(webCamTask);
    webCamThread.setDaemon(true);
    webCamThread.start();
  }

//    BufferedImage img;
//  public void start() {
//
//    stopCamera = false;
//
//    Task<Void> task = new Task<Void>() {
//
//      @Override
//      protected Void call() {
//
//        final AtomicReference<WritableImage> ref = new AtomicReference<>();
//
////        webCam.setViewSize(new Dimension(100, 200));
//        webCam.setCustomViewSizes(new Dimension(10, 10));
//
//        while (!stopCamera) {
////          try {
//          if ((img = webCam.getImage()) != null) {
//            ref.set(SwingFXUtils.toFXImage(img, ref.get()));
//            img.flush();
//            Platform.runLater(() -> imageProperty.set(ref.get()));
////
////            Platform.runLater(() -> {
////              final Image mainimage = SwingFXUtils.toFXImage(img, ref.get());
////              imageProperty.set(mainimage);
////            });
//
//            img.flush();
//
//          }
//        }
////          } catch (Exception e) {
////            e.printStackTrace();
////          }
////        }
//widthLabel
//        return null;
//      }
//    };
//
//    Thread th = new Thread(task);
//    th.setDaemon(true);
//    th.start();
//    imageView.imageProperty().bind(imageProperty);
//  }

  public void start() {

    stopCamera = false;

    Task<Void> task = new Task<Void>() {

      @Override
      protected Void call() throws Exception {

        final AtomicReference<WritableImage> ref = new AtomicReference<>();
        BufferedImage img;

        while (!stopCamera) {
//          try {
            if ((img = webCam.getImage()) != null) {

//              int x = (int) vXSlider.getValue() + 1;
//              int width = (int) vWidthSlider.getValue() + 1;

//              if(x + width <= img.getWidth()) {
//                BufferedImage subImg = img.getSubimage(
//                  (int) vXSlider.getValue() + 1,
//                  (int) vYSlider.getValue() + 1,
//                  (int) vWidthSlider.getValue() + 1,
//                  (int) vHeightSlider.getValue() + 1
//                );
//                ref.set(Util.toFXImage(subImg, ref.get()));
                ref.set(Util.toFXImage(img, ref.get()));
                img.flush();

                Platform.runLater(() -> imageProperty.set(ref.get()));
//              }
            }
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
        }

        return null;
      }
    };

    Thread th = new Thread(task);
    th.setDaemon(true);
    th.start();
    imageView.imageProperty().bind(imageProperty);
  }


  public void stopWebcam() {
    stopCamera = true;
    if(webCam != null) {
      webCam.close();
    }
  }

  @Override
  public void stop() {
    stopWebcam();
  }

}
