import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.FreecellModelCreator.GameType;

import java.io.InputStreamReader;

/**
 * Driver used in testing the {@code SimpleFreecellController} class.
 */
public class TestDriver {

  /**
   * Main method for running code.
   */
  public static void main(String[] args) {
    FreecellModel<ICard> model = FreecellModelCreator.create(GameType.MULTIMOVE);
    try {
      new SimpleFreecellController(model,
          new InputStreamReader(System.in), System.out).playGame(model.getDeck(),
          16, 4, false);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
