package com.printer.swing.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.print.Printable.PAGE_EXISTS;

public class TicketGraphic {
  private static final Logger log = LoggerFactory.getLogger(TicketGraphic.class);

  public static double paperWidth;
  public static double paperHeight;

  public static int imageableX;
  public static int imageableY;
  public static int imageableWidth;
  public static int imageableHeight;

  public static int spaceBetweenLines = 0;
  public static int leftPadding = 0;

  public static int leftMargin = 3;
  public static int rightMargin = 3;
  public static int topMargin = 0;
  public static int bottomMargin = 0;
  public static int rotate = 0;

  public static String fontName = "MONOSPACED";
  public static int fontSize = 9;
  public static int fontStyle = Font.PLAIN;

  private static JTextArea textarea = new JTextArea();

  private List<String> lines;

  public static Font font = new Font(fontName, fontStyle, fontSize);

  private static FontMetrics fontMetrics;

  private static int fontHeight;

  public static void changeFont(final String newFontName) {
    font = new Font(newFontName, fontStyle, fontSize);
    fontMetrics = textarea.getFontMetrics(font);
    fontHeight = fontMetrics.getHeight();
  }

  public static void changeBold(final boolean isBoldActive) {
    fontStyle = isBoldActive ? Font.BOLD : Font.PLAIN;
    font = new Font(fontName, fontStyle, fontSize);
    fontMetrics = textarea.getFontMetrics(font);
    fontHeight = fontMetrics.getHeight();
  }

  public static void changenFontSize(final int newFontSize) {
    font = new Font(fontName, fontStyle, newFontSize);
    fontSize = newFontSize;
    fontMetrics = textarea.getFontMetrics(font);
    fontHeight = fontMetrics.getHeight();
  }

  public TicketGraphic() {
    paperWidth = 164;   // 58mm          -> 3;//3.25
    paperHeight = Utils.millimetersToPixels(100); // 11.69
    imageableX = 0;
    imageableY = 0;
    imageableWidth = (int) (paperWidth - leftMargin - (rightMargin + 24));
    imageableHeight = (int) (paperHeight - topMargin - bottomMargin);

//    textarea.append("A=C=E=G=======Ñ===========\n");
//    textarea.append("===========================\n");
//    textarea.append("ABCDEFGHIJKLMNÑOPQRSTUVWXYZ\n");
//    textarea.append("===========================");

    textarea.append("=========================\n");
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
    textarea.append("=========================");

    textarea.setEditable(false);

    lines = getLines();
    fontMetrics = textarea.getFontMetrics(font);
    fontHeight = fontMetrics.getHeight();
  }

  public int redraw(final Graphics g, final JPanel canvasPanel) {
    Graphics2D g2 = (Graphics2D) g;

    imageableX = canvasPanel.getX();
    imageableY = canvasPanel.getY() + topMargin;
    imageableWidth = (int) (paperWidth - leftMargin - (rightMargin + 24));
    imageableHeight = (int) (paperHeight - topMargin - bottomMargin);

//    redrawCanvasPanels(g, canvasPanel);
    redrawPaper(g, canvasPanel);
    redrawPrintableArea(g, canvasPanel);
    redrawText(g2, imageableX);

    return PAGE_EXISTS;
  }

  public void redrawCanvasPanels(final Graphics g, final JPanel canvasPanel) {
    BorderFactory.createLineBorder(GREEN).paintBorder(canvasPanel, g, canvasPanel.getX(), canvasPanel.getY(), canvasPanel.getWidth(), canvasPanel.getHeight());
  }

  public void redrawPaper(final Graphics g, final JPanel canvasPanel) {
    BorderFactory.createLineBorder(BLUE).paintBorder(canvasPanel, g,
      canvasPanel.getX(), canvasPanel.getY(),
      (int) paperWidth - 24, (int) paperHeight);
  }

  public void redrawPrintableArea(final Graphics g, final JPanel canvasPanel) {
    BorderFactory.createLineBorder(RED).paintBorder(canvasPanel, g, imageableX + leftMargin, imageableY, imageableWidth, imageableHeight);
  }

  public void redrawText(final Graphics2D g2, final int refX) {
    g2.translate(refX + leftMargin + leftPadding, imageableY);
    g2.setFont(font);
    Rectangle2D textBound = bound(g2);
    g2.setColor(Color.BLACK);
    g2.draw(textBound);
    g2.rotate(Math.toRadians(rotate), textBound.getX() + textBound.getWidth() / 2, textBound.getY() + textBound.getHeight() / 2);

    int fontHeight = fontMetrics.getHeight();
    for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
      g2.drawString(lines.get(lineNumber), 0, ((lineNumber + 1) * (fontHeight + spaceBetweenLines)));
    }

  }

  private Rectangle2D bound(final Graphics2D g2) {
    int textWidth = 0;
    int textHeight = fontHeight;

    for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
      Rectangle2D stringBounds = fontMetrics.getStringBounds(lines.get(lineNumber), g2);
      textWidth = (int) Math.max(textWidth, stringBounds.getWidth());
      if (lineNumber > 0) {
        textHeight += ((lineNumber + 1) * (fontHeight + spaceBetweenLines));
      }
    }

    return new Rectangle(0, 0, textWidth, textHeight);
  }

  private List<String> getLines() {
    try {
      List<String> lines = new ArrayList();
      for (int lineNumber = 0; lineNumber < textarea.getLineCount(); lineNumber++) {
        int lineStart = textarea.getLineStartOffset(lineNumber);
        int lineEnd = textarea.getLineEndOffset(lineNumber);
        lines.add(textarea.getText(lineStart, lineEnd - lineStart));
      }
      return lines;
    } catch (final BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

//  public void redrawLayout(final Graphics g, final JPanel canvasPanel) {
//    Graphics2D g2d = (Graphics2D) g;
//    Rectangle2D.Double rect = new Rectangle2D.Double();
//    rect.setRect(canvasPanel.getX(), canvasPanel.getY(), paper.getWidth(), paper.getHeight());
//    g2d.draw(rect);
//  }

}
