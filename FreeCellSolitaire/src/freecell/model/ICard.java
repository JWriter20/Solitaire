package cs3500.freecell.model;

/**
 * Specifies operations that can be used to retrieve a card's data.
 */
public interface ICard {

  /**
   * Checks if a card is valid.
   *
   * @return a boolean representing whether or not the card is valid
   */
  boolean isValid();


  /**
   * Gets the value of the card (A = 1, 2 = 2 ... K = 13).
   *
   * @return the integer representing the value
   */

  int getVal();

  /**
   * Gets the suit of a card.
   *
   * @return the char representing the suit
   */

  char getSuit();

  /**
   * Checks if a card is red.
   *
   * @return the boolean representing whether or not the card is red
   */
  boolean isRed();
}
