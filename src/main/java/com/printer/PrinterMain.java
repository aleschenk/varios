package com.printer;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.QueuedJobCount;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class PrinterMain {

  private static final char ESC = 27; //escape
  private static final char P = 80; //10cpi pitch

  public static void main(String[] args) throws Exception {
    new PrinterMain().init();
//    PrinterG2D.main(args);
  }

  public void init() throws Exception {
//    printAllPrintersNames();
    printAllPrinterAttribues("DiestroPrinter");

    PrintService printService = lookupPrintServiceByName("DiestroPrinter").get();
    PrinterName printerNameAttribute = printService.getAttribute(PrinterName.class);
    ColorSupported colorSupportedAttribute = printService.getAttribute(ColorSupported.class);
    PrinterInfo printerInfo = printService.getAttribute(PrinterInfo.class);
    PrinterIsAcceptingJobs printerIsAcceptingJobsAttribute = printService.getAttribute(PrinterIsAcceptingJobs.class);
    PDLOverrideSupported pDLOverrideSupportedAttribute = printService.getAttribute(PDLOverrideSupported.class);
    QueuedJobCount queuedJobCountAttribute = printService.getAttribute(QueuedJobCount.class);

    System.out.println("Printer Name: " + printerNameAttribute);
    System.out.println("ColorSupported: " + colorSupportedAttribute);
    System.out.println("PrinterInfo: " + printerInfo);
    System.out.println("PrinterIsAcceptingJobs: " + printerIsAcceptingJobsAttribute);
    System.out.println("PDLOverrideSupported: " + pDLOverrideSupportedAttribute);
    System.out.println("QueuedJobCount: " + queuedJobCountAttribute);


    Arrays.stream(printService.getSupportedDocFlavors()).forEach(docFlavor -> System.out.println(docFlavor));

    final String text = formatText("Hello World !");

    lookupPrintServiceByName
      .apply("DiestroPrinter")
      .ifPresent(ps -> print(printService, text));
  }

  private String formatText(final String text) {
    char[] chars = text.toCharArray();
    char[] finalText = new char[chars.length + 2];

    finalText[0] = (char) 0x1B;
    finalText[1] = (char) 0x50;
    for(int i = 2; i < finalText.length - 2; i++) {
      finalText[i] = text.charAt(i);
    }
    return String.valueOf(finalText);
  }

  private void printAllPrinterAttribues(final String printerName) {
    lookupPrintServiceByName.apply(printerName).ifPresent(printService ->
      Arrays.stream(printService.getAttributes().toArray())
        .forEach(attribute -> System.out.println("Name: " + attribute.getName() + " Category: " + attribute.getCategory())));
  }

  private void print(final PrintService printService, final String text) {
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.TEXT_PLAIN_US_ASCII;
//    DocFlavor flavor = DocFlavor.STRING.TEXT_PLAIN;

//    String textToPrint = text + (char) 0x1B + (char) 0x40 + (char) 0x1D + (char) 0x56 + (char) 0x42 + (char) 0x00;
    String textToPrint = text;

    PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
    attributes.add(OrientationRequested.REVERSE_PORTRAIT);

    try {
      Doc myDoc = new SimpleDoc(textToPrint.getBytes(US_ASCII), flavor, null);
//      Doc myDoc = new SimpleDoc(textToPrint.cha, flavor, null);
      DocPrintJob printJob = printService.createPrintJob();
      System.out.println("Printing please wait.");
      printJob.print(myDoc, attributes);
      System.out.println("The job is done.");
    } catch (final Exception e) {
      e.printStackTrace();
    }

  }

  private void printAllPrintersNames() {
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    for (PrintService printService : printServices) {
      System.out.println(printService.getName());
    }
  }

  private Optional<PrintService> lookupPrintServiceByName(final String printerName) {
    return lookupPrintServiceByName.apply(printerName);
  }

  private final Function<String, Optional<PrintService>> lookupPrintServiceByName = printerName -> Arrays
    .stream(PrintServiceLookup.lookupPrintServices(null, null))
    .filter(printService -> printService.getName().equals(printerName))
    .findFirst();


}
