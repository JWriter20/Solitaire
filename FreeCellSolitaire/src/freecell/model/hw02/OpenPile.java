package cs3500.freecell.model.hw02;


import cs3500.freecell.model.ICard;
import java.util.Stack;

/**
 * Represents a pile in the Open slot that can contain a maximum of one card.
 */
public class OpenPile extends Stack<ICard> {

  @Override
  public synchronized ICard pop() {
    return super.pop();
  }

  /**
   * Views the card in an OpenPile if there is one, otherwise returns null.
   *
   * @return  the card in an OpenPile if there is one, otherwise returns null
   */
  @Override
  public synchronized ICard peek() {
    if (isEmpty()) {
      return null;
    } else {
      return super.peek();
    }
  }

  /**
   * Adds a card to the Openpile, or errors if you are trying to add to an already full pile.
   *
   * @param item The item to be added to the Openpile.
   *
   * @throws IllegalArgumentException   If you add to an already full pile.
   *
   * @return  The argument that was passed in.
   */
  @Override
  public ICard push(ICard item) throws IllegalArgumentException {
    if (this.size() > 0) {
      throw new IllegalArgumentException("Cannot add to a pile that already has an elem");
    } else {
      return super.push(item);
    }
  }
}
