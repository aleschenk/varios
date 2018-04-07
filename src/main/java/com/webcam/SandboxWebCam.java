package com.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SandboxWebCam {

  public static void main(String[] args) throws IOException {
    Webcam.setDriver(new V4l4jDriver());
    Webcam webcam = Webcam.getDefault();
    webcam.open();
    ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
  }

}
