package com.printer.swing;

import com.printer.Printers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class PrinterG2D {

  private static final Logger log = LoggerFactory.getLogger(PrinterG2D.class);

  public static void main(String[] args) throws Exception {
    new PrinterG2D().init();
  }
  static final double ONE_INCH_IN_MM = 24.4;
  static final int PRINTER_UNITS = 72;

  static final double PAPER_WIDTH_IN_MM = 58;
  static final double PAPER_WIDTH_IN_INCH = PAPER_WIDTH_IN_MM / ONE_INCH_IN_MM;
  static final double PAPER_WIDTH_IN_POINTS = PAPER_WIDTH_IN_INCH * PRINTER_UNITS;

  static final double PAPER_HEIGHT_IN_MM = 120;
  static final double PAPER_HEIGHT_IN_INCH = PAPER_HEIGHT_IN_MM / ONE_INCH_IN_MM;
  static final double PAPER_HEIGHT_IN_POINTS = PAPER_HEIGHT_IN_INCH * PRINTER_UNITS;

  public void init() throws Exception {

    PageFormat format = new PageFormat();
    Paper paper = new Paper();

    double paperWidth = PAPER_WIDTH_IN_POINTS;//58mm          -> 3;//3.25
    double paperHeight = PAPER_HEIGHT_IN_POINTS;//11.69

    double leftMargin = 0.1 * PRINTER_UNITS;
    double rightMargin = 0.1 * PRINTER_UNITS;
    double topMargin = 0 * PRINTER_UNITS;
    double bottomMargin = 0.01 * PRINTER_UNITS;

    paper.setSize(PAPER_WIDTH_IN_POINTS, PAPER_HEIGHT_IN_POINTS);

    paper.setImageableArea(leftMargin, topMargin,
      (paperWidth - leftMargin - rightMargin),
      (paperHeight - topMargin - bottomMargin));

    format.setPaper(paper);

    log.info("Paper Size (points): {} width {} height", paper.getWidth(), paper.getHeight());
    log.info("Paper Size (inches): {} width {} height", paper.getWidth() / PRINTER_UNITS, paper.getHeight() / PRINTER_UNITS);

    log.info("Imageable Area: {} X {} Y {} width {} height", paper.getImageableX(), paper.getImageableY(),
      paper.getImageableWidth(), paper.getImageableHeight());

    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    aset.add(OrientationRequested.REVERSE_PORTRAIT);

    PrinterJob printerJob = PrinterJob.getPrinterJob();
    Printable printable = new ReceiptPrint();

    PrintService printService = Printers.lookupPrintServiceByName("DiestroPrinter").get();

    format = printerJob.validatePage(format);
    boolean don = printerJob.printDialog();
//    printerJob.setPrintService(printService);

    printerJob.setPrintable(printable, format);
    try {
      printerJob.print(aset);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
