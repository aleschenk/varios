package com.javafx;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_ImageView_Viewport extends Application {
     
    @Override
    public void start(Stage primaryStage) {
         
        ImageView imageView1 = new ImageView(new Image("http://goo.gl/kYEQl"));
         
        //Example to rotate ImageView
        Image image2 = new Image("http://goo.gl/kYEQl");
        Rectangle2D viewportRect2 = new Rectangle2D(
                image2.getWidth()/4, 
                image2.getHeight()/4, 
                image2.getWidth()*3/4, 
                image2.getHeight()*3/4);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setViewport(viewportRect2);
         
        Slider sliderRotate = new Slider();
        sliderRotate.setMin(0);
        sliderRotate.setMax(360);
        sliderRotate.setValue(0);
        sliderRotate.valueProperty().addListener(
                (ObservableValue<? extends Number> observable, 
                        Number oldValue, Number newValue) -> {
            imageView2.setRotate((double)newValue);
        });
         
        //Example to change ViewPort
        Image image3 = new Image("http://goo.gl/kYEQl");
        Rectangle2D viewportRect3 = new Rectangle2D(
                0, 
                0, 
                image3.getWidth(), 
                image3.getHeight());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setViewport(viewportRect3);
         
        Slider sliderViewPort = new Slider();
        sliderViewPort.setMin(0);
        sliderViewPort.setMax(1.0);
        sliderViewPort.setValue(1.0);
        sliderViewPort.valueProperty().addListener(
                (ObservableValue<? extends Number> observable, 
                        Number oldValue, Number newValue) -> {
            Rectangle2D newViewportRect3 = new Rectangle2D(
                    15,
                    15,
                    (double)newValue*image3.getWidth(),
                    (double)newValue*image3.getHeight());
            imageView3.setViewport(newViewportRect3);
        });
         
 
        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageView1, 
                imageView2, sliderRotate,
                imageView3, sliderViewPort);
         
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
         
        Scene scene = new Scene(root, 300, 350);
         
        primaryStage.setTitle("java-buddy: ImageVIew ViewPort");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
}