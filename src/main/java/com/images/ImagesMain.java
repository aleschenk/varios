package com.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class ImagesMain extends Thread {

  @Override
  public synchronized void start() {
    super.start();
  }

  @Override
  public void run() {
    super.run();
  }

  public static void main(String[] args) {
    int y = 1;
    int x = 8;
    
    while(y <= x) {
      y = y *2;
    }

    System.out.println(y);

  }

//  public static void main(String[] args) throws IOException {
//
//    BufferedImage image = read(new File("5.png"));
//
//    System.out.println("Height: " + image.getHeight());
//    System.out.println("Width: " + image.getWidth());
//
//    for (int h = 0; h < image.getHeight(); h++) {
//      for (int w = 0; w < image.getWidth(); w++) {
//        //amountPixel++;
//
//        long rgb = image.getRGB(w, h);
//        long red = (rgb >> 16 ) & 0x000000FF;
//        long green = (rgb >> 8 ) & 0x000000FF;
//        long blue = (rgb) & 0x000000FF;
//
//        if (red == 0 && green == 0 && blue == 0) {
//          System.out.print("#");
//        } else {
//          System.out.print(" ");
//        }
//
////        System.out.printf("R: %d G: %d B: %d => %d (%X)\n", red, green, blue, rgb, rgb);
//      }
//      System.out.println();
//    }
//  }
}
