package com.javafx;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import sun.awt.image.IntegerComponentRaster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.nio.IntBuffer;

public class Util {

  public static WritableImage toFXImage(BufferedImage bimg, WritableImage wimg) {
    int bw = bimg.getWidth();
    int bh = bimg.getHeight();
    switch(bimg.getType()) {
      case 2:
      case 3:
        break;
      default:
        BufferedImage converted = new BufferedImage(bw, bh, 3);
        Graphics2D g2d = converted.createGraphics();
        g2d.drawImage(bimg, 0, 0, (ImageObserver)null);
        g2d.dispose();
        bimg = converted;
    }

    int[] empty;
    if (wimg != null) {
      int iw = (int)wimg.getWidth();
      int ih = (int)wimg.getHeight();
      if (iw >= bw && ih >= bh) {
        if (bw < iw || bh < ih) {
          empty = new int[iw];
          PixelWriter pw = wimg.getPixelWriter();
          PixelFormat<IntBuffer> pf = PixelFormat.getIntArgbPreInstance();
          if (bw < iw) {
            pw.setPixels(bw, 0, iw - bw, bh, pf, empty, 0, 0);
          }

          if (bh < ih) {
            pw.setPixels(0, bh, iw, ih - bh, pf, empty, 0, 0);
          }
        }
      } else {
        wimg = null;
      }
    }

    if (wimg == null) {
      wimg = new WritableImage(bw, bh);
    }

    PixelWriter pw = wimg.getPixelWriter();
    IntegerComponentRaster icr = (IntegerComponentRaster)bimg.getRaster();
    empty = icr.getDataStorage();
    int offset = icr.getDataOffset(0);
    int scan = icr.getScanlineStride();
    PixelFormat<IntBuffer> pf = bimg.isAlphaPremultiplied() ? PixelFormat.getIntArgbPreInstance() : PixelFormat.getIntArgbInstance();
    pw.setPixels(0, 0, bw, bh, pf, empty, offset, scan);
    return wimg;
  }

}
