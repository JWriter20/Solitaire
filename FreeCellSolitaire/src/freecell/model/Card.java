package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Represents a Card from a stanard 13 value 4 suit deck.
 */
public class Card implements ICard {
  private final char suit;
  private final int val;
  private final boolean isRed;


  /**
   * Constructs a {@code Card} object.
   *
   * @param suit   The card's suit
   * @param val  The value of the card
   */
  public Card(char suit, int val) {
    this.suit = suit;
    this.val = val;
    if (suit == '♣' || suit == '♠') {
      this.isRed = false;
    } else {
      this.isRed = true;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      return ((Card) obj).val == this.val && ((Card) obj).suit == this.suit;

    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    Integer v = this.val;
    Character c = this.suit;
    return v.hashCode() + c.hashCode();
  }

  @Override
  public String toString() {
    switch (this.val) {
      case 1:
        return "A" + this.suit;
      case 11:
        return "J" + this.suit;
      case 12:
        return "Q" + this.suit;
      case 13:
        return "K" + this.suit;
      default:
        return "" + this.val + this.suit;
    }
  }

  @Override
  public boolean isValid() {
    List<Character> suits = new ArrayList<Character>(Arrays.asList('♣', '♠', '♥', '♦'));
    return (this.val > 0 && this.val < 14 && suits.contains(this.suit));
  }

  @Override
  public int getVal() {
    return val;
  }

  @Override
  public char getSuit() {
    return suit;
  }

  @Override
  public boolean isRed() {
    return this.isRed;
  }
}