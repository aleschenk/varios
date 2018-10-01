package com.printer.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class ReceiptPrint implements Printable {

  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
    return redraw(graphics, pageFormat, pageIndex);
  }

  public static int redraw(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
    int lineStart;           // start index of line in textarea
    int lineEnd;             // end index of line in textarea
    int lineNumber;
    int lineCount;

    Graphics2D g2d = (Graphics2D) graphics;

    if (pageIndex < 0 || pageIndex >= 1) {
      return Printable.NO_SUCH_PAGE;
    }

    JTextArea textarea = new JTextArea();
    textarea.append("=======================\n");
    textarea.append("Informe de Calibración\n");
    textarea.append("#null\n");
    textarea.append("2018/05/13  13:00:00\n");
    textarea.append("Diestro S/N: 8977\n");
    textarea.append("UI: 0.1 uG: uGas 0.10\n");
    textarea.append("  -----------------------\n");
    textarea.append("  Pack LOTE: 1\n");
    textarea.append("Vence: 2018/05/13\n");
    textarea.append("Buff. A: 1500% B: 125%\n");
    textarea.append("Gas.  A: 113%  B: 110%\n");
    textarea.append("Rinse  : 117%\n");
    textarea.append("pAtm   : 1000.0 mmHg\n");
    textarea.append("  -----------------------\n");
    textarea.append("  pH = Calibrado\n");
    textarea.append("\n");
    textarea.append("Ganancia: 103.0\n");
    textarea.append(" Balance: 203.0 mV\n");
    textarea.append("Deriva A: 303.0\n");
    textarea.append("Deriva B: 403.0\n");
    textarea.append("  -----------------------\n");
    textarea.append("  CO2 = Calibrado\n");
    textarea.append("\n");
    textarea.append("Ganancia: 101.0\n");
    textarea.append(" Balance: 201.0 mV\n");
    textarea.append("Deriva A: 301.0 mmHg\n");
    textarea.append("Deriva B: 401.0 mmHg\n");
    textarea.append("  -----------------------\n");
    textarea.append("  O2 = Calibrado\n");
    textarea.append("Err UNST INV SAT\n");
    textarea.append("Ganancia: 102.0\n");
    textarea.append(" Balance: 202.0 mV\n");
    textarea.append("Deriva A: 302.0 mmHg\n");
    textarea.append("Deriva B: 402.0 mmHg\n");
    textarea.append("  =======================\n");
    textarea.setEditable(false);

    g2d.translate(pageFormat.getImageableX() + 100, pageFormat.getImageableY());
//    g2d.rotate(Math.PI);
    Font font = new Font("MONOSPACED", Font.PLAIN, 9);
    g2d.setFont(font);
    lineNumber = 0;
    lineCount = textarea.getLineCount();
    String strText = textarea.getText();

    while (lineCount != 0) {
      try {
        lineStart = textarea.getLineStartOffset(lineNumber);
        lineEnd = textarea.getLineEndOffset(lineNumber);
        strText = textarea.getText(lineStart, lineEnd - lineStart);
      } catch (Exception exception) {
        System.out.println("Printing error:" + exception);                  // have to catch BadLocationException
      }

      g2d.drawString(strText, 1, (lineNumber + 1) * 20);
      lineNumber = lineNumber + 1;
      lineCount--;
    }
    return Printable.PAGE_EXISTS;
  }
}