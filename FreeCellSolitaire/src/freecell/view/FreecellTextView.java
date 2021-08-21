package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;

import java.io.IOException;
import java.util.Objects;

/*
 Changed toString so that OpenPiles appear above Cascade Piles, this was something I
 did wrong at the start, yet did not realize it until this assignment.
 */


/**
 * Represents the View (UI) of a FreeCell game.
 */
public class FreecellTextView implements FreecellView {

  private FreecellModelState<?> model;
  private Appendable dest;

  /**
   * Constructs a {@code FreecellTextView} object.
   *
   * @param model  The model of the FreecellTextView
   * @param dest  The appendable where outputs are written for FreecellTextView
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable dest)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null Input model");
    }

    this.dest = dest;
    this.model = model;
  }
  /**
   * Constructs a {@code FreecellTextView} object.
   * Appendable is set to System.out when no Appendable arg is provided
   *
   * @param model  The model of the FreecellTextView
   */

  public FreecellTextView(FreecellModelState<?> model)
      throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Null Input model");

    }

    this.dest = System.out;
    this.model = model;
  }

  @Override
  public void renderBoard() throws IOException {
    dest.append(this.toString());
    this.dest.append('\n');
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.dest.append(message);

  }

  @Override
  public String toString() {
    if (model.getNumOpenPiles() == -1) {
      return "";
    }

    String returnStr = "";
    for (int i = 0; i < 4; i++) {
      returnStr += String.format("F%d:", i + 1);
      for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
        if (j == model.getNumCardsInFoundationPile(i) - 1) {
          returnStr += " " + Objects.toString(model.getFoundationCardAt(i, j).toString(), "");
        } else {
          returnStr += " " + Objects.toString(model.getFoundationCardAt(i, j).toString() + ",", "");
        }
      }
      returnStr += "\n";
    }

    for (int i = 0; i < model.getNumOpenPiles(); i++) {
      returnStr += String.format("O%d:", i + 1);
      for (int j = 0; j < model.getNumCardsInOpenPile(i); j++) {
        if (j == model.getNumCardsInOpenPile(i) - 1) {
          returnStr += " " + Objects.toString(model.getOpenCardAt(i).toString(), "");
        } else {
          returnStr += " " + Objects.toString(model.getOpenCardAt(i).toString() + ",", "");
        }
      }
      returnStr += "\n";
    }

    for (int i = 0; i < model.getNumCascadePiles(); i++) {
      returnStr += String.format("C%d:", i + 1);
      for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
        if (j == model.getNumCardsInCascadePile(i) - 1) {
          returnStr += " " + Objects.toString(model.getCascadeCardAt(i, j).toString(), "");
        } else {
          returnStr += " " + Objects.toString(model.getCascadeCardAt(i, j).toString() + ",", "");
        }
      }
      if (i != model.getNumCascadePiles() - 1) {
        returnStr += "\n";
      }
    }

    return returnStr;
  }
}
