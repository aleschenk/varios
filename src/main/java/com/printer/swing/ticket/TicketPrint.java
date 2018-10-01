package com.printer.swing.ticket;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;

public class TicketPrint implements Printable {

  private static final double PAPER_WIDTH_IN_MM = 58;
  private static final double PAPER_HEIGHT_IN_MM = 290;

  private final TicketGraphic tg;

  public TicketPrint(final TicketGraphic tg) {
    this.tg = tg;
  }

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
    if (pageIndex < 0 || pageIndex >= 1) {
      return Printable.NO_SUCH_PAGE;
    }

    Paper paper = new Paper();
    double width = Utils.toDPI(PAPER_WIDTH_IN_MM);
    double height = Utils.toDPI(PAPER_HEIGHT_IN_MM);

    paper.setSize(width, height);
    paper.setImageableArea(0,0, 164, 200);

    pageFormat.setPaper(paper);

    tg.redrawText((Graphics2D) graphics, 0);

    return Printable.PAGE_EXISTS;
  }

}
