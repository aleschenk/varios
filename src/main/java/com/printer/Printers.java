package com.printer;

import com.printer.swing.ticket.TicketGraphic;
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

public class Printers {

  private static final Logger log = LoggerFactory.getLogger(Printers.class);

  public static void print(final Printable printable) {
    PageFormat pageFormat = new PageFormat();
    Paper paper = new Paper();

    paper.setSize(TicketGraphic.paperWidth, TicketGraphic.paperHeight);
    paper.setImageableArea(TicketGraphic.leftMargin, TicketGraphic.topMargin,
      (TicketGraphic.paperWidth - TicketGraphic.leftMargin - TicketGraphic.rightMargin),
      (TicketGraphic.paperHeight - TicketGraphic.topMargin - TicketGraphic.bottomMargin));

//    paper.setImageableArea(7, topMargin,
//      (paperWidth - leftMargin - rightMargin),
//      (paperHeight - topMargin - bottomMargin));
    pageFormat.setPaper(paper);
    log.info("Paper Size: {} (Width) x {} (Height)", pageFormat.getWidth(), pageFormat.getHeight());
    log.info("Page Imageable: X:{} Y: {} Width: {} Height: {}", pageFormat.getImageableX(), pageFormat.getImageableY(),
      pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

    try {
      PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
      aset.add(OrientationRequested.REVERSE_PORTRAIT);

      PrinterJob printerJob = PrinterJob.getPrinterJob();

      PrintService printService = Printers.lookupPrintServiceByName("DiestroPrinter").get();

//      pageFormat = printerJob.validatePage(pageFormat);
//    boolean don = printerJob.printDialog();
      printerJob.setPrintService(printService);

      printerJob.setPrintable(printable, pageFormat);
      printerJob.print(aset);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Optional<PrintService> lookupPrintServiceByName(final String printerName) {
    return lookupPrintServiceByName.apply(printerName);
  }

  public static Function<String, Optional<PrintService>> lookupPrintServiceByName = printerName -> Arrays
    .stream(PrintServiceLookup.lookupPrintServices(null, null))
    .filter(printService -> printService.getName().equals(printerName))
    .findFirst();

}
