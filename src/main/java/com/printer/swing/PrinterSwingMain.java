package com.printer.swing;

import com.printer.swing.ticket.TicketGraphic;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.util.List;

import static java.util.Arrays.asList;

public class PrinterSwingMain {
  private final TicketPanel ticketPanel = new TicketPanel();
  private TicketGraphic tg;

  public static void main(final String[] args) {
    new PrinterSwingMain().init();
  }

  public void init() {
    tg = ticketPanel.tg;
    setup();
    bind();
    JFrame mainFrame = new JFrame();
    mainFrame.setSize(new Dimension(1024, 768));
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    mainFrame.add(ticketPanel);
    mainFrame.setVisible(true);
  }

  private void setup() {
    ticketPanel.lineSpacingSlide.setValue(TicketGraphic.spaceBetweenLines);
    ticketPanel.topMarginSlider.setValue(TicketGraphic.topMargin);
    ticketPanel.leftPaddingSlide.setValue(TicketGraphic.leftPadding);
    ticketPanel.leftMarginSlide.setValue(TicketGraphic.leftMargin);
    ticketPanel.rightMarginSlide.setValue(TicketGraphic.rightMargin);
//    ticketPanel.paperSizeSlide.setValue();
    ticketPanel.rotateSlider.setValue(TicketGraphic.rotate);
    ticketPanel.fontSizeSlider.setValue(TicketGraphic.fontSize);

    ticketPanel.lineSpacingValueLabel.setText(String.valueOf(ticketPanel.lineSpacingSlide.getValue()));
    ticketPanel.topMarginValueLabel.setText(String.valueOf(ticketPanel.topMarginSlider.getValue()));
    ticketPanel.leftPaddingValueLabel.setText(String.valueOf(ticketPanel.leftPaddingSlide.getValue()));
    ticketPanel.leftMarginValueLabel.setText(String.valueOf(ticketPanel.leftMarginSlide.getValue()));
    ticketPanel.rightMarginValueLabel.setText(String.valueOf(ticketPanel.rightMarginSlide.getValue()));
    ticketPanel.paperSizeValueLabel.setText(String.valueOf(ticketPanel.paperSizeSlide.getValue()));
    ticketPanel.rotateValueLabel.setText(String.valueOf(ticketPanel.rotateSlider.getValue()));
    ticketPanel.fontSizeValueLabel.setText(String.valueOf(ticketPanel.fontSizeSlider.getValue()));
  }

  private void bind() {
    ticketPanel.lineSpacingSlide.addChangeListener(e -> {
      TicketGraphic.spaceBetweenLines = ticketPanel.lineSpacingSlide.getValue();
      ticketPanel.lineSpacingValueLabel.setText(String.valueOf(ticketPanel.lineSpacingSlide.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.topMarginSlider.addChangeListener(e -> {
      TicketGraphic.topMargin = ticketPanel.topMarginSlider.getValue();
      ticketPanel.topMarginValueLabel.setText(String.valueOf(ticketPanel.topMarginSlider.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.leftPaddingSlide.addChangeListener(e -> {
      TicketGraphic.leftPadding = ticketPanel.leftPaddingSlide.getValue();
      ticketPanel.leftPaddingValueLabel.setText(String.valueOf(ticketPanel.leftPaddingSlide.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.leftMarginSlide.addChangeListener(e -> {
      TicketGraphic.leftMargin = ticketPanel.leftMarginSlide.getValue();
      ticketPanel.leftMarginValueLabel.setText(String.valueOf(ticketPanel.leftMarginSlide.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.rightMarginSlide.addChangeListener(e -> {
      TicketGraphic.rightMargin = ticketPanel.rightMarginSlide.getValue();
      ticketPanel.rightMarginValueLabel.setText(String.valueOf(ticketPanel.rightMarginSlide.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.paperSizeSlide.addChangeListener(e -> {
      TicketGraphic.paperWidth = ticketPanel.paperSizeSlide.getValue();
      ticketPanel.paperSizeValueLabel.setText(String.valueOf(ticketPanel.paperSizeSlide.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.rotateSlider.addChangeListener(e -> {
      TicketGraphic.rotate = ticketPanel.rotateSlider.getValue();
      ticketPanel.rotateValueLabel.setText(String.valueOf(ticketPanel.rotateSlider.getValue()));
      ticketPanel.repaint();
    });

    ticketPanel.fontSizeSlider.addChangeListener(e -> {
      TicketGraphic.changenFontSize(ticketPanel.fontSizeSlider.getValue());
      ticketPanel.fontSizeValueLabel.setText(String.valueOf(ticketPanel.fontSizeSlider.getValue()));
      ticketPanel.repaint();
    });

    allFonts().stream().forEach(ticketPanel.fontComboBox::addItem);
    ticketPanel.fontComboBox.setSelectedIndex(defaultFontIndex(TicketGraphic.fontName));

    ticketPanel.fontComboBox.addActionListener(e -> {
      TicketGraphic.changeFont(findFontByIndex(ticketPanel.fontComboBox.getSelectedIndex()));
      ticketPanel.repaint();
    });

    ticketPanel.boldCheckBox.addActionListener(e -> {
      TicketGraphic.changeBold(ticketPanel.boldCheckBox.isSelected());
      ticketPanel.repaint();
    });

    ticketPanel.rotateValueLabel.getFont();
  }

  private int defaultFontIndex(final String preferredFontName) {
    int liberationSerifIndex = findFontIndex(preferredFontName);
    int arialIndex = findFontIndex("Arial");
    return Math.max(liberationSerifIndex, arialIndex);
  }

  private int findFontIndex(final String font) {
    List<String> fonts = allFonts();
    for (int i = 0; i < fonts.size(); i++) {
      if (fonts.get(i).equalsIgnoreCase(font)) {
        return i;
      }
    }
    return 0;
  }

  private String findFontByIndex(final int index) {
    return allFonts().get(index);
  }

  public List<String> allFonts() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    return asList(ge.getAvailableFontFamilyNames());
  }

//  private void loadAllFonts() {
//    ticketPanel.fontComboBox.setRenderer(new FontCellRenderer());
//    List<String> allFonts = allFonts();
//    // Total 185.
//    // Con 121-122 explota.
//    for(int i = 0; i < 120; i++) {
//      String font = allFonts.get(i);
//      ticketPanel.fontComboBox.addItem(font);
//    }
//  }


//  class FontCellRenderer extends DefaultListCellRenderer {
//    public Component getListCellRendererComponent(
//      JList list,
//      Object value,
//      int index,
//      boolean isSelected,
//      boolean cellHasFocus) {
//      JLabel label = (JLabel) super.getListCellRendererComponent(
//        list, value, index, isSelected, cellHasFocus);
//      Font font = new Font((String) value, Font.PLAIN, 20);
//      label.setFont(font);
//      return label;
//    }
//  }

}
