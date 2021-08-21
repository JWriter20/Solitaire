package cs3500.freecell.model;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveModel;

/**
 * An enumeration class that creates a GameType variable that can either be a SINGLEMOVE
 * or a MULTIMOVE.
 */
public class FreecellModelCreator {

  /**
   * The Enum used in the factory class used to differentiate whether or not you are playing
   * a singleMove game of freecell solitaire or a multimove one.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }


  /**
   * A factory class used to either create a Multimove or Singlemove game.
   *
   * @param type the GameType representing what type of model is going to be used.
   * @return a FreecellModel representing which type of game is going to be played.
   * @throws IllegalArgumentException if the type is null, or not a GameType
   */
  public static FreecellModel<ICard> create(GameType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Invalid Gametype");
    }

    switch (type) {
      case MULTIMOVE:
        return new MultiMoveModel();
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      default:
        throw new IllegalArgumentException("Invalid Gametype");

    }
  }
}
