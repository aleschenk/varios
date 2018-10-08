package com.printer;

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class PrinterMain {

  public static void main(String[] args) throws Exception {
    new PrinterMain().init();
  }

  public void init() throws Exception {
//    printAllPrintersNames();
    printAllPrinterAttribues("cashino");

    PrintService printService = lookupPrintServiceByName("cashino").get();
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

    final String text = "Hello World !";

    lookupPrintServiceByName
      .apply("cashino")
      .ifPresent(ps -> print(printService, text));
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

    try {
      Doc myDoc = new SimpleDoc(textToPrint.getBytes(US_ASCII), flavor, null);
//      Doc myDoc = new SimpleDoc(textToPrint.cha, flavor, null);
      DocPrintJob printJob = printService.createPrintJob();
      PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
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
