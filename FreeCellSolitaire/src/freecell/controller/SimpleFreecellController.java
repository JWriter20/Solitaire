package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Controller of a Freecell Game. This is what allows
 * the user to interact with the Game allowing them to play moves.
 * Will Make them redo a move if it is Invalid in some way.
 */
public class SimpleFreecellController implements FreecellController<ICard> {

  private final Readable rd;
  private final Appendable ap;
  private FreecellModel<ICard> model;

  /**
   * Constructs a {@code SimpleFreecellController} object and makes sure none of the
   * Inputs are null.
   */
  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (rd == null || ap == null || model == null) {
      throw new IllegalArgumentException("Null input or output");
    }

    this.rd = rd;
    this.ap = ap;
    this.model = model;
  }

  /**
   * Plays a game of Freecell Solitaire.
   *
   * @param deck        the deck to be used to play this game
   * @param numCascades the number of cascade piles
   * @param numOpens    the number of open piles
   * @param shuffle     shuffle the deck if true, false otherwise
   * @throws IllegalStateException if writing to the Appendable object used by it fails
   *                    or reading from the provided Readable fails
   *                    or if there are no remaining inputs
   *                    and the game has not yet ended.
   * @throws IllegalArgumentException If any of the inputs or moves are invalid or null values
   *                    are provided
   */
  @Override
  public void playGame(List<ICard> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {

    FreecellTextView view = new FreecellTextView(model, ap);

    if (deck == null) {
      throw new IllegalArgumentException("Null Deck Input");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (Exception e) {
      try {
        view.renderMessage("Could not start game.");
        return;
      } catch (IOException er) {
        throw new IllegalStateException();
      }
    }

    Scanner s = new Scanner(this.rd);
    /*
    try {
      view.renderBoard();
      System.out.println();
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    System.out.print("Your move: ");
    if (!s.hasNext()) {
      throw new IllegalStateException("No Inputs Remaining");
    }
    */
    String[] inputs;
    int[] intVals;

    while (!model.isGameOver()) {
      try {
        view.renderBoard();
        System.out.println();
      } catch (IOException e) {
        throw new IllegalStateException();
      }

      int i = 0;
      inputs = new String[3];
      intVals = new int[3];
      System.out.print("Your move: ");

      while (intVals[2] == 0) {
        if (!s.hasNext()) {
          throw new IllegalStateException("No Inputs Remaining");
        }
        String input = s.next();
        if (input.equals("Q") || input.equals("q")) {
          try {
            view.renderMessage("Game quit prematurely.");
            return;
          } catch (IOException e) {
            throw new IllegalStateException();
          }
        }

        /*
        if ((s.findInLine("Q") != null || s.findInLine("q") != null)) {
          try {
            view.renderMessage("Game quit prematurely.");
            return;
          } catch (IOException e) {
            throw new IllegalStateException();
          }
        }
        */
        if (i % 2 == 0) {
          if (validPile(input)) {
            inputs[i] = input;
            intVals[i] = Integer.parseInt(input.substring(1));
            i++;
          } else {
            try {
              view.renderMessage("Invalid move. Try again. " + input + " Discarded. "
                  + "Finding next valid Pile.\n");
            } catch (IOException e) {
              throw new IllegalStateException();
            }
          }
        } else {
          try {
            int val = Integer.parseInt(input);
            if (val <= 0) {
              throw new IllegalArgumentException("Out of Bounds");
            }

            inputs[1] = input;
            intVals[1] = val;

            i++;
          } catch (IllegalArgumentException e) {
            try {
              view.renderMessage("Invalid move. Try again. Finding next valid Card.\n");
            } catch (IOException err) {
              throw new IllegalStateException();
            }
          }
        }
      }


      // Check to make sure the formatting of the three inputs is correct.
      // Checks chars, but does not returns them
      char pt1 = inputs[0].charAt(0);
      char pt2 = inputs[2].charAt(0);

      try {
        model.move(getPT(pt1), intVals[0] - 1,
            intVals[1] - 1, getPT(pt2), intVals[2] - 1);
      } catch (IllegalArgumentException e) {
        try {
          view.renderMessage("Invalid move. Try again. Move not Executed.\n");
        } catch (IOException err) {
          throw new IllegalStateException();
        }
      }
    }

    try {
      view.renderBoard();
      view.renderMessage("\nGame over.");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  private boolean validPile(String pile) {
    String validChars = "CFO";
    if (validChars.indexOf(pile.charAt(0)) != -1) {
      try {
        if (Integer.parseInt(pile.substring(1)) > 0) {
          switch (pile.charAt(0)) {
            case 'C':
              return Integer.parseInt(pile.substring(1)) <= model.getNumCascadePiles();
            case 'O':
              return Integer.parseInt(pile.substring(1)) <= model.getNumOpenPiles();
            case 'F':
              return Integer.parseInt(pile.substring(1)) <= 4;
            default:
              return false;
          }
        } else {
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }

    } else {
      return false;
    }

  }

  private PileType getPT(char pt) throws IllegalArgumentException {
    switch (pt) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("Bad Input");
    }
  }
  /*
  private boolean validInt(String s) throws IllegalArgumentException {
    try {
      int i = Integer.parseInt(s);
      return i > 0;
    } catch (Exception e) {
      return false;
    }
  }
  */

}
